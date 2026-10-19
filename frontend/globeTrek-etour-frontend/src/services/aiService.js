import api from "./api";

const getRecommendation = (request) => {
  return api.post("/ai/recommend", request);
};

const getItinerary = (packageId) => {
  return api.post("/ai/itinerary", { packageId });
};

const chat = (messages) => {
  return api.post("/ai/chat", { messages });
};

export default {
  getRecommendation,
  getItinerary,
  chat,
};