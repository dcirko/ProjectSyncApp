package hr.projectsyncspring.projectsyncjavaspring.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
