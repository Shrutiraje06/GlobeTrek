package com.indiatour.etour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indiatour.etour.entity.Itinerary;
import com.indiatour.etour.repository.ItineraryRepository;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Override
    public Itinerary saveItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    @Override
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    @Override
    public Itinerary getItineraryById(Long id) {
        return itineraryRepository.findById(id).orElse(null);
    }

    @Override
    public Itinerary updateItinerary(Long id, Itinerary itinerary) {

        Itinerary existingItinerary = itineraryRepository.findById(id).orElse(null);

        if (existingItinerary != null) {

            existingItinerary.setDayNumber(itinerary.getDayNumber());
            existingItinerary.setTitle(itinerary.getTitle());
            existingItinerary.setDescription(itinerary.getDescription());
            existingItinerary.setTourPackage(itinerary.getTourPackage());

            return itineraryRepository.save(existingItinerary);
        }

        return null;
    }

    @Override
    public void deleteItinerary(Long id) {
        itineraryRepository.deleteById(id);
    }
}