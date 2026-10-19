package com.indiatour.etour.dto.response;

public class AIItineraryResponse {

    private String itinerary;

    public AIItineraryResponse() {
    }

    public AIItineraryResponse(String itinerary) {
        this.itinerary = itinerary;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }
}