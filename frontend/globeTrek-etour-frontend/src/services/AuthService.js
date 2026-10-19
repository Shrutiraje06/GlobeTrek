import axios from "axios";

const API_URL = "http://localhost:9090/api/auth/";

const login = (email, password) => {
  return axios.post(API_URL + "login", {
    email,
    password,
  });
};

const register = (user) => {
  return axios.post(API_URL + "register", user);
};

const logout = () => {
  localStorage.removeItem("token");
};

const authService = {
  login,
  register,
  logout,
};

export default authService;