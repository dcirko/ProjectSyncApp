package hr.projectsyncspring.projectsyncjavaspring.projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String deadline;
    private String status;
    private Long createdById;
    private Long teamId;
}
