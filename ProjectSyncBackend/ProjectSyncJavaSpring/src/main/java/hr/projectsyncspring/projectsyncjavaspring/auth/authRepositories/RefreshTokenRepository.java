package hr.projectsyncspring.projectsyncjavaspring.auth.authRepositories;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Modifying
    @Transactional
    void deleteByUser_Id(Long userId);

    Optional<RefreshToken> findByToken(String token);
}
