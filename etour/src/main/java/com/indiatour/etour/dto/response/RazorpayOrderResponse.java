package com.indiatour.etour.dto.response;

public class RazorpayOrderResponse {

    private String orderId;
    private String keyId;
    private Double amount;
    private String currency;

    public RazorpayOrderResponse(String orderId, String keyId, Double amount, String currency) {
        this.orderId = orderId;
        this.keyId = keyId;
        this.amount = amount;
        this.currency = currency;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getKeyId() {
        return keyId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}