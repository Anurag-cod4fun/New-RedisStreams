package com.example.demotwo.dto;

public class EventEnvelope {
    private String eventType;
    private User user;

    public EventEnvelope() {}

    public EventEnvelope(String eventType, User user) {
        this.eventType = eventType;
        this.user = user;
    }

    // Getters and setters
}
