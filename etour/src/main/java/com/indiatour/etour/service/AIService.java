package com.indiatour.etour.service;

import com.indiatour.etour.dto.request.AIChatRequest;
import com.indiatour.etour.dto.request.AIItineraryRequest;
import com.indiatour.etour.dto.request.AIRecommendRequest;
import com.indiatour.etour.dto.response.AIChatResponse;
import com.indiatour.etour.dto.response.AIItineraryResponse;
import com.indiatour.etour.dto.response.AIRecommendResponse;

public interface AIService {
    AIRecommendResponse getRecommendation(AIRecommendRequest request);
    AIItineraryResponse generateItinerary(AIItineraryRequest request);
    AIChatResponse chat(AIChatRequest request);
}