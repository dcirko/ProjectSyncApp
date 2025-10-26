package hr.projectsyncspring.projectsyncjavaspring.projects;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDTO> getTwoNewestProjectsByUserId(Long userId);
}
