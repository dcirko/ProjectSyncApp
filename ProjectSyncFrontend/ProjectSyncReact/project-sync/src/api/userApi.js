import axios from "axios";
import api from "./interceptor";


export const getCurrentUser = () => {
  return api.get("/user/getCurrentUser", { withCredentials: true });
};

export const getProfile = (userId) => {
  return api.get(`/user/getProfile/${userId}`, { withCredentials: true });
};

export const getAllUsersForAdmin = () =>{
  return api.get("/user/getAllUsersForAdmin", { withCredentials: true });
}

export const isAdmin = () =>{
  return api.get("/user/isAdmin", { withCredentials: true });
}

