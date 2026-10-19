import api from "./api";

const createBooking = (booking) => {
  return api.post("/bookings", booking);
};

const getBookingById = (id) => {
  return api.get(`/bookings/${id}`);
};

const updateBooking = (id, booking) => {
  return api.put(`/bookings/${id}`, booking);
};

const getBookingHistory = () => {
  return api.get("/bookings/history");
};

const getAllBookings = () => {
  return api.get("/bookings");
};

const makePayment = (payment) => {
  return api.post("/payments", payment);
};
const createRazorpayOrder = (bookingId) => {
  return api.post("/payments/razorpay/create-order", { bookingId });
};

const verifyRazorpayPayment = (data) => {
  return api.post("/payments/razorpay/verify", data);
};


export default {
  createBooking,
  getBookingById,
  updateBooking,
  getBookingHistory,
  getAllBookings,
  makePayment,
  createRazorpayOrder,
  verifyRazorpayPayment,
};