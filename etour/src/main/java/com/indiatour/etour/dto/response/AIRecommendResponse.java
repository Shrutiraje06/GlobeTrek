package com.indiatour.etour.dto.response;

public class AIRecommendResponse {

    private String recommendation;

    public AIRecommendResponse() {
    }

    public AIRecommendResponse(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}