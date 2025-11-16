package hr.projectsyncspring.projectsyncjavaspring.teams;

import hr.projectsyncspring.projectsyncjavaspring.projects.ProjectResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeamResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Long createdById;
    private Set<ProjectResponseDTO> projects;
    private Set<TeamMemberDTO> members;
}
