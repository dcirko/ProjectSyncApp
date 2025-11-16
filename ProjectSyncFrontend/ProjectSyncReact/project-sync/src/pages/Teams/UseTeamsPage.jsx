import { useEffect, useState } from "react";
import { 
  getAllUsersForAdmin, 
  getCurrentUser, 
  isAdmin 
} from "../../api/userApi";
import { 
  addNewMember, 
  addNewTeam, 
  getAllTeamsByUserId, 
  getAllTeamsForAdmin 
} from "../../api/teamsApi";

export function useTeamsPage() {
  const [admin, setAdmin] = useState(false);
  const [newTeam, setNewTeam] = useState({ name: '', description: '', createdById: null, membersIds: [] });
  const [users, setUsers] = useState([]);
  const [user, setUser] = useState(null);
  const [allTeams, setAllTeams] = useState([]);
  const [allTeamsByUser, setAllTeamsByUser] = useState([]);

  useEffect(() => {
    Promise.all([isAdmin(), getCurrentUser()])
      .then(([adminRes, userRes]) => {
        setAdmin(adminRes.data);
        setUser(userRes.data);
      })
  }, []);

  useEffect(() => {
    if (!user?.id) return;

    if (admin) {
      getAllUsersForAdmin().then(res => setUsers(res.data));
      getAllTeamsForAdmin().then(res => setAllTeams(res.data));
    }

    getAllTeamsByUserId(user.id).then(res => setAllTeamsByUser(res.data));
  }, [admin, user]);

    function refreshTeams() {
        getAllTeamsForAdmin()
        .then(res => setAllTeams(res.data));
    }

    function refreshNewTeam(){
        setnewTeam({
            name: '',
            description: '',
            createdById: user.id,
            membersIds: []
        });
    }


  return {admin, newTeam, users, user, allTeams, allTeamsByUser, refreshTeams, refreshNewTeam};
}