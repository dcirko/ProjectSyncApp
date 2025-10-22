package hr.projectsyncspring.projectsyncjavaspring.controllers;

import hr.projectsyncspring.projectsyncjavaspring.authDomain.AuthRequestDTO;
import hr.projectsyncspring.projectsyncjavaspring.authDomain.JwtResponseDTO;
import hr.projectsyncspring.projectsyncjavaspring.authDomain.RefreshToken;
import hr.projectsyncspring.projectsyncjavaspring.authDomain.RegisterRequestDTO;
import hr.projectsyncspring.projectsyncjavaspring.authServices.AuthService;
import hr.projectsyncspring.projectsyncjavaspring.authServices.JwtService;
import hr.projectsyncspring.projectsyncjavaspring.authServices.MyUserDetailsService;
import hr.projectsyncspring.projectsyncjavaspring.authServices.RefreshTokenService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

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
