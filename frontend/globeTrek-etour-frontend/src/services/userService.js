import api from "./api";

const getAllUsers = () => {
  return api.get("/admin/users");
};

export default {
  getAllUsers,
};