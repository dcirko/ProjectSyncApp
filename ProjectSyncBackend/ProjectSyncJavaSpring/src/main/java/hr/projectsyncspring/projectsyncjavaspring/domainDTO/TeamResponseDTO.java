package hr.projectsyncspring.projectsyncjavaspring.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeamResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Long createdById;

}
