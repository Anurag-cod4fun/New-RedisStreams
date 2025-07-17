package com.example.demotwo.dto;
import java.io.Serializable;

public class EventEnvelope implements Serializable {
    private static final long serialVersionUID = 1L;
    private String eventType;
    private User user;

    public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public User getUser() {
		return user;
	}

    public EventEnvelope() {}

    public EventEnvelope(String eventType, User user) {
        this.eventType = eventType;
        this.user = user;
    }

    // Getters and setters
}
