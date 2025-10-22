package hr.projectsyncspring.projectsyncjavaspring.domainDTO;

import hr.projectsyncspring.projectsyncjavaspring.domainEnum.TaskPriority;
import hr.projectsyncspring.projectsyncjavaspring.domainEnum.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {
    @NotBlank(message = "Task name is required")
    private String name;

    @Size(max = 1000, message = "Description can contain up to 1000 characters")
    private String description;

    @NotNull(message = "Project ID is required")
    private Long projectId;

    @NotNull(message = "Assigned user ID is required")
    private Long assignedToId;

    @NotNull(message = "Status is required")
    private String status;

    @NotNull(message = "Priority is required")
    private String priority;

    @FutureOrPresent(message = "Deadline must be in the present or future")
    private LocalDateTime deadline;
}
