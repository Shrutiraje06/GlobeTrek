import api from "./api";

const getAllPackages = () => {
  return api.get("/packages");
};

const getPackageById = (id) => {
  return api.get(`/packages/${id}`);
};

const addPackage = (data) => {
  return api.post("/packages", data);
};

const updatePackage = (id, data) => {
  return api.put(`/packages/${id}`, data);
};

const deletePackage = (id) => {
  return api.delete(`/packages/${id}`);
};

export default {
  getAllPackages,
  getPackageById,
  addPackage,
  updatePackage,
  deletePackage,
};