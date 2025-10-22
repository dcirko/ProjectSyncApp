package hr.projectsyncspring.projectsyncjavaspring.controllers;

import hr.projectsyncspring.projectsyncjavaspring.authDomain.MyUserResponseDTO;
import hr.projectsyncspring.projectsyncjavaspring.authServices.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
