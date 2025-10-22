package hr.projectsyncspring.projectsyncjavaspring.services;

import hr.projectsyncspring.projectsyncjavaspring.domainDTO.ProjectResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDTO> getTwoNewestProjectsByUserId(Long userId);
}
