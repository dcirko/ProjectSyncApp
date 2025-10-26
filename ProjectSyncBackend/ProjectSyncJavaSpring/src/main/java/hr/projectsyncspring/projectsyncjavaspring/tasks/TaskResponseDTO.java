package hr.projectsyncspring.projectsyncjavaspring.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private Long assignedToId;
    private String status;
    private String priority;
    private String deadline;
    //private Long createdById;

}
