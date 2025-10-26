package hr.projectsyncspring.projectsyncjavaspring.auth.authServices;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.AuthRequestDTO;
import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.RegisterRequestDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> logout(String refreshToken);
    ResponseEntity<?> login(AuthRequestDTO authRequestDTO, HttpServletResponse response);
    ResponseEntity<?> register(RegisterRequestDTO registerRequestDTO);
    ResponseEntity<?> refreshToken(String refreshToken, HttpServletResponse response);
}
