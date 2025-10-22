import axios from "axios";

const API_URL = "http://localhost:8080/api/auth";

export const login = (data) => axios.post(`${API_URL}/login`, data, { withCredentials: true });
export const register = (data) => axios.post(`${API_URL}/register`, data, { withCredentials: true });
export const logout = () => axios.post(`${API_URL}/logout`, {}, { withCredentials: true });