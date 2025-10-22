package hr.projectsyncspring.projectsyncjavaspring.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String name;
    private Long assignedToId;
    private String description;
    private String deadline;
    private String status;
    private String priority;
    private Long createdById;
    private Long projectId;
}
