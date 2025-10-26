package hr.projectsyncspring.projectsyncjavaspring.tasks;

import java.util.List;

public interface TaskService {
    List<TaskResponseDTO> findTop2NewestTasksByUserId(Long userId);
}
