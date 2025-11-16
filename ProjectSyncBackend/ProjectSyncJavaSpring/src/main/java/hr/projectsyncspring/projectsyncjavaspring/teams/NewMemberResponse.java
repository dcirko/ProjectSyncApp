package hr.projectsyncspring.projectsyncjavaspring.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NewMemberResponse {
    private Long teamId;
    private Long userId;
}
