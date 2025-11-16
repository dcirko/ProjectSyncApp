import api from "./interceptor";

export const getTwoNewestTasksByUserId = (userId) => {return api.get(`/tasks/getTwoNewestTasksByUserId/${userId}`);}