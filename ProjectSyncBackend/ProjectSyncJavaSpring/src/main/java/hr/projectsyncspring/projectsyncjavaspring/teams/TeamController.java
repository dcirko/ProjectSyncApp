package hr.projectsyncspring.projectsyncjavaspring.teams;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class TeamController {
    private TeamService teamService;

    @GetMapping("/get2NewestTeamsByUserId/{userId}")
    public List<TeamResponseDTO> get2NewestTeamsByUserId(@PathVariable Long userId){
        return teamService.get2NewestTeamsByUserId(userId);
    }

    @GetMapping("/getAllTeamsForAdmin")
    public List<TeamResponseDTO> getAllTeamsForAdmin(){
        return teamService.getAllTeamsForAdmin();
    }

    @GetMapping("/getAllTeamsByUserId/{userId}")
    public List<TeamResponseDTO> getAllTeamsByUserId(@PathVariable Long userId){
        return teamService.getAllTeamsByUserId(userId);
    }

    @PostMapping("/addNewTeam")
    public ResponseEntity<?> addNewTeam(@RequestBody @Valid TeamRequestDTO teamRequestDTO){
        teamService.addNewTeam(teamRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addNewMember")
    public ResponseEntity<?> addNewMemberToTeam(@RequestBody NewMemberResponse newMemberResponse){
        System.out.println("Member: " + newMemberResponse);
        teamService.addNewMemberToTeam(newMemberResponse);
        return ResponseEntity.ok().build();
    }
}
