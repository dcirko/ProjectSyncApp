package hr.projectsyncspring.projectsyncjavaspring.teams;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT DISTINCT t FROM Team t " +
            "LEFT JOIN FETCH t.members m " +
            "LEFT JOIN FETCH m.user u " +
            "LEFT JOIN FETCH t.projects p")
    List<Team> findAllWithMembersAndProjects();


    @Query("SELECT DISTINCT t FROM Team t " +
            "LEFT JOIN FETCH t.members " +
            "LEFT JOIN FETCH t.projects " +
            "WHERE t.id IN (" +
            "  SELECT tm.team.id FROM TeamMember tm WHERE tm.user.id = :userId" +
            ") ORDER BY t.createdAt DESC")
    List<Team> findTop2ByUserIdWithMembersAndProjects(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT DISTINCT t FROM Team t " +
            "LEFT JOIN FETCH t.members " +
            "LEFT JOIN FETCH t.projects " +
            "WHERE t.id IN (" +
            "  SELECT tm.team.id FROM TeamMember tm WHERE tm.user.id = :userId" +
            ")")
    List<Team> findAllByMembersUserId(@Param("userId") Long userId);
}
