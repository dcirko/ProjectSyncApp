package hr.projectsyncspring.projectsyncjavaspring.auth.authServices;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.MyUser;
import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.MyUserResponseDTO;
import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.Role;
import hr.projectsyncspring.projectsyncjavaspring.auth.authRepositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
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

    public MyUserResponseDTO getProfile(Long userId){
        System.out.println("id:" + userId);
        MyUser user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        System.out.println(user);
        return myUserToMyUserDTO(user);
    }

    public MyUser getById(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public Boolean isAdmin(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUser user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return user.getRole().name().equals("ADMIN");
    }

    public List<MyUserResponseDTO> getAllUsersForAdmin(){
        return userRepository.findAllByRole(Role.USER).stream().map(this::myUserToMyUserDTO).toList();
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
