package hr.projectsyncspring.projectsyncjavaspring.teams;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.MyUser;
import hr.projectsyncspring.projectsyncjavaspring.projects.Project;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"projects", "members"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    private MyUser createdBy;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<>();
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TeamMember> members = new HashSet<>();
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
