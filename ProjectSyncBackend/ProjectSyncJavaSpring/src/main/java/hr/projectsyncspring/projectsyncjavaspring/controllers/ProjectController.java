package hr.projectsyncspring.projectsyncjavaspring.controllers;

import hr.projectsyncspring.projectsyncjavaspring.domainDTO.ProjectResponseDTO;
import hr.projectsyncspring.projectsyncjavaspring.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/getTwoNewestProjectsByUserId/{userId}")
    public List<ProjectResponseDTO> getTwoNewestProjectsByUserId(@PathVariable Long userId){
        System.out.println("Getting two newest projects by user id: " + userId);
        return projectService.getTwoNewestProjectsByUserId(userId);
    }
}
