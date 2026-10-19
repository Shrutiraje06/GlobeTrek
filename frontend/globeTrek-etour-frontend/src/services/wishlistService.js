import api from "./api";

const addToWishlist = (packageId) => {
  return api.post(`/wishlist/${packageId}`);
};

const getWishlist = () => {
  return api.get("/wishlist");
};

const removeFromWishlist = (packageId) => {
  return api.delete(`/wishlist/${packageId}`);
};

const checkWishlisted = (packageId) => {
  return api.get(`/wishlist/check/${packageId}`);
};

export default {
  addToWishlist,
  getWishlist,
  removeFromWishlist,
  checkWishlisted,
};