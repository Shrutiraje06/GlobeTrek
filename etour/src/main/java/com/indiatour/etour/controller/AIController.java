package com.indiatour.etour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.dto.request.AIChatRequest;
import com.indiatour.etour.dto.request.AIItineraryRequest;
import com.indiatour.etour.dto.request.AIRecommendRequest;
import com.indiatour.etour.dto.response.AIChatResponse;
import com.indiatour.etour.dto.response.AIItineraryResponse;
import com.indiatour.etour.dto.response.AIRecommendResponse;
import com.indiatour.etour.service.AIService;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/recommend")
    public AIRecommendResponse recommend(@RequestBody AIRecommendRequest request) {
        return aiService.getRecommendation(request);
    }

    @PostMapping("/itinerary")
    public AIItineraryResponse itinerary(@RequestBody AIItineraryRequest request) {
        return aiService.generateItinerary(request);
    }

    @PostMapping("/chat")
    public AIChatResponse chat(@RequestBody AIChatRequest request) {
        return aiService.chat(request);
    }
}