package hr.projectsyncspring.projectsyncjavaspring.authServices;

import hr.projectsyncspring.projectsyncjavaspring.authDomain.AuthRequestDTO;
import hr.projectsyncspring.projectsyncjavaspring.authDomain.JwtResponseDTO;
import hr.projectsyncspring.projectsyncjavaspring.authDomain.RegisterRequestDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> logout(String refreshToken);
    ResponseEntity<?> login(AuthRequestDTO authRequestDTO, HttpServletResponse response);
    ResponseEntity<?> register(RegisterRequestDTO registerRequestDTO);
    ResponseEntity<?> refreshToken(String refreshToken, HttpServletResponse response);
}
