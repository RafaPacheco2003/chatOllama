package org.chatboot.dtos;

import java.time.LocalDateTime;

public class ChatResponse {
    private String message;
    private String conversationId;
    private LocalDateTime timestamp;

    public ChatResponse(String message, String conversationId, LocalDateTime timestamp) {

        this.message = message;
        this.conversationId = conversationId;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
