import api from "./interceptor";

export const get2NewestTeamsByUserId = (userId) => {
    return api.get(`/teams/get2NewestTeamsByUserId/${userId}`);
}
export const addNewTeam = (newTeam) => {
    return api.post("/teams/addNewTeam", newTeam);
}

export const getAllTeamsForAdmin = () => {
    return api.get("/teams/getAllTeamsForAdmin")
}

export const getAllTeamsByUserId = (userId) => {
    return api.get(`/teams/getAllTeamsByUserId/${userId}`)
}

export const addNewMember = (newMember) => {
    return api.post("/teams/addNewMember", newMember)
}