package hr.projectsyncspring.projectsyncjavaspring.domain;

import hr.projectsyncspring.projectsyncjavaspring.authDomain.MyUser;
import hr.projectsyncspring.projectsyncjavaspring.domainEnum.TeamRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "team_members")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser user;
    @Enumerated(EnumType.STRING)
    private TeamRole role;
}
