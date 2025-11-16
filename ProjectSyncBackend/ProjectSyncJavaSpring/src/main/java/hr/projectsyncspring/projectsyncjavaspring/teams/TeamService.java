package hr.projectsyncspring.projectsyncjavaspring.teams;

import java.util.List;

public interface TeamService {
    List<TeamResponseDTO> get2NewestTeamsByUserId(Long userId);
    List<TeamResponseDTO> getAllTeamsForAdmin();
    List<TeamResponseDTO> getAllTeamsByUserId(Long userId);
    void addNewTeam(TeamRequestDTO teamRequestDTO);
    void addNewMemberToTeam(NewMemberResponse newMemberResponse);
}
