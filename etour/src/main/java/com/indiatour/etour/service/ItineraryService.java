package com.indiatour.etour.service;

import java.util.List;

import com.indiatour.etour.entity.Itinerary;

public interface ItineraryService {

    Itinerary saveItinerary(Itinerary itinerary);

    List<Itinerary> getAllItineraries();

    Itinerary getItineraryById(Long id);

    Itinerary updateItinerary(Long id, Itinerary itinerary);

    void deleteItinerary(Long id);
}