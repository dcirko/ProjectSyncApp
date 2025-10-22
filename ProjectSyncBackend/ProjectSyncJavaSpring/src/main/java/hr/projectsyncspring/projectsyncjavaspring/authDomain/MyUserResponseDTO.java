package hr.projectsyncspring.projectsyncjavaspring.authDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyUserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String role;
}
