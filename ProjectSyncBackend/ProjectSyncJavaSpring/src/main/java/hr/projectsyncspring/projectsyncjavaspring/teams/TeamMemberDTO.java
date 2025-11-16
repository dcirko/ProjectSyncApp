package hr.projectsyncspring.projectsyncjavaspring.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamMemberDTO {
    private Long id;
    private Long teamId;
    private Long userId;
    private String userEmail;
    private String role;
}
