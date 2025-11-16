package hr.projectsyncspring.projectsyncjavaspring.auth.authControllers;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.MyUserResponseDTO;
import hr.projectsyncspring.projectsyncjavaspring.auth.authServices.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/user")
public class UserController {
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/getCurrentUser")
    public MyUserResponseDTO getCurrentUser(){
        return myUserDetailsService.getCurrentUser();
    }

    @GetMapping("/getProfile/{userId}")
    public MyUserResponseDTO getProfile(@PathVariable Long userId){
        return myUserDetailsService.getProfile(userId);
    }

    @GetMapping("/getAllUsersForAdmin")
    public List<MyUserResponseDTO> getAllUsersForAdmin(){
        return myUserDetailsService.getAllUsersForAdmin();
    }

    @GetMapping("/isAdmin")
    public Boolean isAdmin(){
        return myUserDetailsService.isAdmin();
    }
}
