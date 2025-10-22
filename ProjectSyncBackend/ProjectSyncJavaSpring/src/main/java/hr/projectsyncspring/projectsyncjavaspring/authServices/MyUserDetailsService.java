package hr.projectsyncspring.projectsyncjavaspring.authServices;

import hr.projectsyncspring.projectsyncjavaspring.authDomain.MyUser;
import hr.projectsyncspring.projectsyncjavaspring.authDomain.MyUserResponseDTO;
import hr.projectsyncspring.projectsyncjavaspring.authRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MyUser> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        MyUser user = userOpt.get();

        return User.withUsername(user.getEmail()).password(user.getPassword()).roles(user.getRole().name())
                .build();

    }

    public MyUserResponseDTO getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println("Email: " + email);
        MyUser user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User not found"));

        return myUserToMyUserDTO(user);
    }

    private MyUserResponseDTO myUserToMyUserDTO(MyUser user){
        return new MyUserResponseDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
