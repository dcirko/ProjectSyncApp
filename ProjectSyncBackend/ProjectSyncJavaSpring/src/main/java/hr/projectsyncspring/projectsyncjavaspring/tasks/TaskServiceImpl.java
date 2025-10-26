package hr.projectsyncspring.projectsyncjavaspring.tasks;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    @Override
    public List<TaskResponseDTO> findTop2NewestTasksByUserId(Long userId) {
        return taskRepository.findTop2ByAssignedTo_IdOrderByCreatedAtDesc(userId).stream().map(this::taskToResponseDTO).toList();
    }

    private TaskResponseDTO taskToResponseDTO(Task task){
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setProjectId(task.getProject().getId());
        taskResponseDTO.setName(task.getName());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setAssignedToId(task.getAssignedTo().getId());
        taskResponseDTO.setStatus(task.getStatus().name());
        taskResponseDTO.setDeadline(task.getDeadline().toString());
        taskResponseDTO.setPriority(task.getPriority().name());
        return taskResponseDTO;
    }
}
