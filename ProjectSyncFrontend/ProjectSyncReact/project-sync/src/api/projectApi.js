import api from "./interceptor";

export const getTwoNewestProjectsByUserId = (userId) => {
    return api.get(`/project/getTwoNewestProjectsByUserId/${userId}`);
}