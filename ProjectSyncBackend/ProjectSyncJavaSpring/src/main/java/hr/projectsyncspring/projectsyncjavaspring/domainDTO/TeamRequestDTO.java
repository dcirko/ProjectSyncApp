package hr.projectsyncspring.projectsyncjavaspring.domainDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeamRequestDTO {
    @NotBlank(message = "Team name is required")
    private String name;
    @NotBlank(message = "Team description is required")
    @Size(max = 1000, message = "Description can contain up to 1000 characters")
    private String description;
    @NotNull(message = "Created by ID is required")
    private Long createdById;
    private List<Long> projectsIds;
    private List<Long> membersIds;
}
