package hr.projectsyncspring.projectsyncjavaspring.teams;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.MyUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"team", "user"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "team_members")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser user;
    @Enumerated(EnumType.STRING)
    private TeamRole role;
}
