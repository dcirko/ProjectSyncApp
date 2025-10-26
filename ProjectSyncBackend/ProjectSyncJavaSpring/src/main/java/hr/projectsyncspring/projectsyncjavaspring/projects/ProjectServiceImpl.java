package hr.projectsyncspring.projectsyncjavaspring.projects;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    //@Autowired
    private final ProjectRepository projectRepository;
    @Override
    public List<ProjectResponseDTO> getTwoNewestProjectsByUserId(Long userId) {
        return projectRepository.findTop2ByTeamMembersUserIdOrderByCreatedAtDesc(userId).stream().map(this::projectToResponseDTO).toList();
    }

    private ProjectResponseDTO projectToResponseDTO(Project project) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setId(project.getId());
        projectResponseDTO.setName(project.getName());
        projectResponseDTO.setDescription(project.getDescription());
        projectResponseDTO.setDeadline(project.getDeadline().toString());
        projectResponseDTO.setStatus(project.getStatus().name());
        projectResponseDTO.setCreatedById(project.getCreatedBy().getId());
        projectResponseDTO.setTeamId(project.getTeam().getId());
        return projectResponseDTO;
    }
}
