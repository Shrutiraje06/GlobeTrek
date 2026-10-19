package com.indiatour.etour.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.indiatour.etour.dto.request.AIChatRequest;
import com.indiatour.etour.dto.request.AIItineraryRequest;
import com.indiatour.etour.dto.request.AIRecommendRequest;
import com.indiatour.etour.dto.request.ChatMessage;
import com.indiatour.etour.dto.response.AIChatResponse;
import com.indiatour.etour.dto.response.AIItineraryResponse;
import com.indiatour.etour.dto.response.AIRecommendResponse;
import com.indiatour.etour.entity.TourPackage;
import com.indiatour.etour.repository.TourPackageRepository;

@Service
public class AIServiceImpl implements AIService {

    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    // ============================================================
    // LOWEST LEVEL — sends a fully-built request body, parses the reply.
    // Every feature eventually calls this one.
    // ============================================================
    private String sendToGemini(String requestBody) {

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(
                        "https://generativelanguage.googleapis.com/v1beta/models/"
                        + model + ":generateContent"))
                    .header("x-goog-api-key", apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Gemini HTTP status: " + response.statusCode());
            System.out.println("Gemini raw response: " + response.body());

            JsonNode root = mapper.readTree(response.body());

            if (root.has("error")) {
                String errorMessage = root.path("error").path("message").asText();
                return "AI error: " + errorMessage;
            }

            JsonNode candidates = root.path("candidates");

            if (!candidates.isArray() || candidates.isEmpty()) {
                return "Sorry, I couldn't generate a response right now. Please try again.";
            }

            return candidates.get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, something went wrong talking to the AI service.";
        }
    }

    // ============================================================
    // MID LEVEL — builds a SINGLE-turn request (used by Recommend + Itinerary)
    // ============================================================
    private String callGeminiSingleTurn(String systemInstruction, String userMessage) {

        try {
            String requestBody = """
                {
                  "system_instruction": { "parts": [{ "text": %s }] },
                  "contents": [
                    { "role": "user", "parts": [{ "text": %s }] }
                  ]
                }
                """.formatted(
                        mapper.writeValueAsString(systemInstruction),
                        mapper.writeValueAsString(userMessage)
                );

            return sendToGemini(requestBody);

        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, something went wrong.";
        }
    }

    // ============================================================
    // MID LEVEL — builds a MULTI-turn request (used by Chatbot)
    // ============================================================
    private String callGeminiMultiTurn(String systemInstruction, List<ChatMessage> messages) {

        try {
            ObjectNode requestNode = mapper.createObjectNode();

            ObjectNode systemNode = requestNode.putObject("system_instruction");
            ArrayNode systemParts = systemNode.putArray("parts");
            systemParts.addObject().put("text", systemInstruction);

            ArrayNode contents = requestNode.putArray("contents");

            for (ChatMessage message : messages) {
                ObjectNode turn = contents.addObject();
                turn.put("role", message.getRole()); // "user" or "model"
                ArrayNode parts = turn.putArray("parts");
                parts.addObject().put("text", message.getContent());
            }

            return sendToGemini(mapper.writeValueAsString(requestNode));

        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, something went wrong.";
        }
    }

    // ============================================================
    // Shared helper — the catalog text, reused by Recommend AND Chat
    // ============================================================
    private String buildCatalogText() {
        List<TourPackage> packages = tourPackageRepository.findAll();

        return packages.stream()
                .map(p -> String.format(
                        "- [ID:%d] %s | Destination: %s | Duration: %s | Price: Rs.%.0f | %s",
                        p.getPackageId(), p.getPackageName(), p.getDestination(),
                        p.getDuration(), p.getPrice(),
                        p.getDescription() == null ? "" : p.getDescription()))
                .collect(Collectors.joining("\n"));
    }

    // ============================================================
    // FEATURE 1 — Smart Recommendation Engine
    // ============================================================
    @Override
    public AIRecommendResponse getRecommendation(AIRecommendRequest request) {

        String systemInstruction =
                "You are a helpful travel assistant for an app called IndiaTour. " +
                "You must ONLY recommend packages from the catalog below — " +
                "never invent a package that isn't listed, and never invent prices. " +
                "Recommend 1 to 3 best-matching packages and briefly explain why " +
                "each one fits, in a friendly, concise tone (under 150 words).\n\n" +
                "CATALOG:\n" + buildCatalogText();

        String text = callGeminiSingleTurn(systemInstruction, request.getUserQuery());

        return new AIRecommendResponse(text);
    }

    // ============================================================
    // FEATURE 2 — AI Itinerary Generator
    // ============================================================
    @Override
    public AIItineraryResponse generateItinerary(AIItineraryRequest request) {

        TourPackage tourPackage = tourPackageRepository
                .findById(request.getPackageId())
                .orElse(null);

        if (tourPackage == null) {
            return new AIItineraryResponse("Package not found.");
        }

        String systemInstruction = String.format(
                "You are a travel itinerary planner for an app called IndiaTour. " +
                "Generate a day-by-day itinerary for the EXACT trip described below. " +
                "The itinerary must have EXACTLY the number of days implied by the " +
                "duration given — do not add extra days. " +
                "Format it as 'Day 1:', 'Day 2:', etc., each with 2-3 short bullet " +
                "activities. Keep it realistic and concise.\n\n" +
                "Package: %s\n" +
                "Destination: %s\n" +
                "Duration: %s\n" +
                "Description: %s",
                tourPackage.getPackageName(),
                tourPackage.getDestination(),
                tourPackage.getDuration(),
                tourPackage.getDescription() == null ? "" : tourPackage.getDescription()
        );

        String text = callGeminiSingleTurn(
                systemInstruction,
                "Generate the day-by-day itinerary now."
        );

        return new AIItineraryResponse(text);
    }

    // ============================================================
    // FEATURE 3 — AI Chatbot
    // ============================================================
    @Override
    public AIChatResponse chat(AIChatRequest request) {

        String systemInstruction =
                "You are IndiaTour's friendly travel assistant chatbot. " +
                "Answer questions about our tour packages using ONLY the real catalog " +
                "below — never invent a package or price that isn't listed. " +
                "If asked something unrelated to travel/IndiaTour, politely redirect " +
                "the conversation back to travel planning. Keep replies concise.\n\n" +
                "CATALOG:\n" + buildCatalogText();

        String reply = callGeminiMultiTurn(systemInstruction, request.getMessages());

        return new AIChatResponse(reply);
    }
}