import axios from "axios";
import api from "./interceptor";


export const getCurrentUser = () => {
  return api.get("/user/getCurrentUser", { withCredentials: true });
};

