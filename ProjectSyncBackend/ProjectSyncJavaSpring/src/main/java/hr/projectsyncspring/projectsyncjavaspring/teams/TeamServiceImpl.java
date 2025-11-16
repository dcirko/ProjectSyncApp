package hr.projectsyncspring.projectsyncjavaspring.teams;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.MyUser;
import hr.projectsyncspring.projectsyncjavaspring.auth.authRepositories.UserRepository;
import hr.projectsyncspring.projectsyncjavaspring.auth.authServices.MyUserDetailsService;
import hr.projectsyncspring.projectsyncjavaspring.projects.Project;
import hr.projectsyncspring.projectsyncjavaspring.projects.ProjectServiceImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService{
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private ProjectServiceImpl projectServiceImpl;
    private MyUserDetailsService myUserDetailsService;
    private final TeamMemberRepository teamMemberRepository;

    @Override
    @Transactional
    public List<TeamResponseDTO> get2NewestTeamsByUserId(Long userId) {
        List<TeamResponseDTO> teamResponseDTOS = teamRepository.findTop2ByUserIdWithMembersAndProjects(userId, PageRequest.of(0, 2)).stream().map(this::teamToResponseDTO).toList();
        System.out.println(teamResponseDTOS);
        return teamResponseDTOS;
    }

    @Override
    @Transactional
    public List<TeamResponseDTO> getAllTeamsForAdmin() {
        List<TeamResponseDTO> teamResponseDTOS = teamRepository.findAllWithMembersAndProjects().stream().map(this::teamToResponseDTO).toList();
        System.out.println(teamResponseDTOS);
        return teamResponseDTOS;
    }

    @Override
    @Transactional
    public List<TeamResponseDTO> getAllTeamsByUserId(Long userId) {
        return teamRepository.findAllByMembersUserId(userId).stream().map(this::teamToResponseDTO).toList();

    }

    @Override
    public void addNewTeam(TeamRequestDTO teamRequestDTO) {
        Team newTeam = teamRepository.save(requestToTeam(teamRequestDTO));
        System.out.println("team: "+teamRequestDTO);
        for(Long membersId : teamRequestDTO.getMembersIds()){
            MyUser member = userRepository.findById(membersId).orElseThrow();
            TeamMember teamMember = new TeamMember();
            teamMember.setTeam(newTeam);
            teamMember.setUser(member);
            teamMember.setRole(TeamRole.DEVELOPER);
            teamMemberRepository.save(teamMember);
        }
        ResponseEntity.ok().build();
    }

    @Override
    public void addNewMemberToTeam(NewMemberResponse newMemberResponse) {
        TeamMember teamMember = new TeamMember();
        teamMember.setRole(TeamRole.DEVELOPER);
        MyUser member = userRepository.findById(newMemberResponse.getUserId()).orElseThrow();
        teamMember.setUser(member);
        Team team = teamRepository.findById(newMemberResponse.getTeamId()).orElseThrow();
        teamMember.setTeam(team);
        teamMemberRepository.save(teamMember);

        ResponseEntity.ok().build();
    }

    private Team requestToTeam(TeamRequestDTO teamRequestDTO){
        Team team = new Team();
        team.setName(teamRequestDTO.getName());
        team.setDescription(teamRequestDTO.getDescription());
        MyUser creator = myUserDetailsService.getById(teamRequestDTO.getCreatedById());
        team.setCreatedBy(creator);
        return team;
    }

    private TeamResponseDTO teamToResponseDTO(Team team){
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO();
        teamResponseDTO.setId(team.getId());
        teamResponseDTO.setName(team.getName());
        teamResponseDTO.setDescription(team.getDescription());
        teamResponseDTO.setCreatedById(team.getCreatedBy().getId());

        teamResponseDTO.setProjects(team.getProjects().stream().map(projectServiceImpl::projectToResponseDTO).collect(Collectors.toSet()));

        teamResponseDTO.setMembers(team.getMembers().stream().map(this::memberToDto).collect(Collectors.toSet()));

        return teamResponseDTO;
    }




    private TeamMemberDTO memberToDto(TeamMember teamMember){
        TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
        teamMemberDTO.setId(teamMember.getId());
        teamMemberDTO.setTeamId(teamMember.getTeam().getId());
        teamMemberDTO.setUserId(teamMember.getUser().getId());
        teamMemberDTO.setUserEmail(teamMember.getUser().getEmail());
        teamMemberDTO.setRole(teamMember.getRole().name());
        return teamMemberDTO;
    }
}
