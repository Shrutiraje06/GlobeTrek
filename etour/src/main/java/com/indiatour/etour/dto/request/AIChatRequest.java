package com.indiatour.etour.dto.request;

import java.util.List;

public class AIChatRequest {

    private List<ChatMessage> messages;

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
}