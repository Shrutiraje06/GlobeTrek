import api from "./api";

const getDashboard = () => {
  return api.get("/admin/dashboard");
};

export default {
  getDashboard,
};