package hr.projectsyncspring.projectsyncjavaspring.tasks;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping("/getTwoNewestTasksByUserId/{userId}")
    public List<TaskResponseDTO> getTwoNewestTasksByUserId(@PathVariable Long userId){
        return taskService.findTop2NewestTasksByUserId(userId);
    }
}
