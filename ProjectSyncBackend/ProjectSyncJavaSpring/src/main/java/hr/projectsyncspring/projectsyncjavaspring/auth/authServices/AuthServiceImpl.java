package hr.projectsyncspring.projectsyncjavaspring.auth.authServices;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.*;
import hr.projectsyncspring.projectsyncjavaspring.auth.authRepositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> logout(String refreshToken) {
        refreshTokenService.findByToken(refreshToken).ifPresent(refreshTokenService::deleteRefreshToken);

        ResponseCookie responseCookie = ResponseCookie.from("refreshToken", "")
                .path("/")
                .maxAge(0)
                .httpOnly(true)
                .secure(true)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).build();
    }

    @Override
    public ResponseEntity<?> login(AuthRequestDTO authRequestDTO, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDTO.getEmail(),
                            authRequestDTO.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                String email = authRequestDTO.getEmail();
                String accessToken = jwtService.generateToken(email);
                return getJwtResponseDTO(response, email, accessToken);
            } else {
                throw new UsernameNotFoundException("Invalid username or password");
            }

        } catch (Exception e) {
            //throw new UsernameNotFoundException("Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Email ili lozinka nisu ispravni"));
        }
    }


    @Override
    public ResponseEntity<?> register(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByEmail(registerRequestDTO.getEmail()).isEmpty()){
            MyUser newUser = new MyUser();
            newUser.setName(registerRequestDTO.getName());
            newUser.setSurname(registerRequestDTO.getSurname());
            newUser.setEmail(registerRequestDTO.getEmail());
            newUser.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
            newUser.setRole(Role.USER);
            userRepository.save(newUser);
            return ResponseEntity.ok("User registered successfully!");
        }else{
            return ResponseEntity.badRequest().body("User already exists!");
        }

    }

    @Override
    public ResponseEntity<?> refreshToken(String refreshToken, HttpServletResponse response) {
        if(refreshToken == null){
            throw new RuntimeException("Refresh token is missing!");
        }

        RefreshToken refreshTokenTemp = refreshTokenService.findByToken(refreshToken).orElseThrow(() -> new RuntimeException("Refresh token is invalid!"));

        RefreshToken refreshToken1 =  refreshTokenService.verifyExpiration(refreshTokenTemp);
        String email = refreshToken1.getUser().getEmail();
        String accessToken = jwtService.generateToken(email);

        refreshTokenService.deleteRefreshToken(refreshToken1);
        return getJwtResponseDTO(response, email, accessToken);
    }

    private ResponseEntity<?> getJwtResponseDTO(HttpServletResponse response, String email, String accessToken) {
        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(email);


        ResponseCookie cookie = ResponseCookie.from("refreshToken", newRefreshToken.getToken())
                .httpOnly(true)
                .secure(false)
                .path("/api/auth")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(JwtResponseDTO.builder().accessToken(accessToken).build());
    }
}
