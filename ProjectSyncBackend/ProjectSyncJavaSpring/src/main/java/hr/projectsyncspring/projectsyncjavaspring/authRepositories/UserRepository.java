package hr.projectsyncspring.projectsyncjavaspring.authRepositories;

import hr.projectsyncspring.projectsyncjavaspring.authDomain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByEmail(String email);
}
