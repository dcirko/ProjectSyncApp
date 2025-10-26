package hr.projectsyncspring.projectsyncjavaspring.auth.authControllers;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.AuthRequestDTO;
import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.RegisterRequestDTO;
import hr.projectsyncspring.projectsyncjavaspring.auth.authServices.AuthService;
import hr.projectsyncspring.projectsyncjavaspring.auth.authServices.JwtService;
import hr.projectsyncspring.projectsyncjavaspring.auth.authServices.MyUserDetailsService;
import hr.projectsyncspring.projectsyncjavaspring.auth.authServices.RefreshTokenService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;
    private final RefreshTokenService refreshTokenService;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse response){
        return authService.login(authRequestDTO, response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
        return authService.register(registerRequestDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "refreshToken", required = false) String refreshToken){
        return authService.logout(refreshToken);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@CookieValue(name = "refreshToken", required = false) String refreshToken, HttpServletResponse response){
        return authService.refreshToken(refreshToken, response);
    }
}
