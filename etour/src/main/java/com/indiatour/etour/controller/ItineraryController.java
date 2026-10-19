package com.indiatour.etour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.entity.Itinerary;
import com.indiatour.etour.service.ItineraryService;

@RestController
@RequestMapping("/itineraries")
@CrossOrigin(origins = "*")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    // Save Itinerary
    @PostMapping
    public Itinerary saveItinerary(@RequestBody Itinerary itinerary) {
        return itineraryService.saveItinerary(itinerary);
    }

    // Get All Itineraries
    @GetMapping
    public List<Itinerary> getAllItineraries() {
        return itineraryService.getAllItineraries();
    }

    // Get Itinerary By ID
    @GetMapping("/{id}")
    public Itinerary getItineraryById(@PathVariable Long id) {
        return itineraryService.getItineraryById(id);
    }

    // Update Itinerary
    @PutMapping("/{id}")
    public Itinerary updateItinerary(@PathVariable Long id,
                                     @RequestBody Itinerary itinerary) {

        return itineraryService.updateItinerary(id, itinerary);
    }

    // Delete Itinerary
    @DeleteMapping("/{id}")
    public String deleteItinerary(@PathVariable Long id) {

        itineraryService.deleteItinerary(id);

        return "Itinerary deleted successfully.";
    }
}