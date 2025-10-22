package hr.projectsyncspring.projectsyncjavaspring.domainDTO;

import hr.projectsyncspring.projectsyncjavaspring.domainEnum.ProjectStatus;
import jakarta.validation.constraints.Future;
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
public class ProjectRequestDTO {
    @NotBlank(message = "Project name is required")
    private String name;

    @Size(max = 1000, message = "Description can contain up to 1000 characters")
    private String description;

    @Future(message = "Deadline must be in the future")
    private LocalDateTime deadline;

    @NotNull(message = "Project status is required")
    private ProjectStatus status;

    @NotNull(message = "Creator ID is required")
    private Long createdById;
}
