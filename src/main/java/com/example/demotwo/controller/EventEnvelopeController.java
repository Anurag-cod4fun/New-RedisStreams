package com.example.demotwo.controller;
import com.example.demotwo.dto.EventEnvelope;
import com.example.demotwo.dto.User;
import com.example.demotwo.service.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventEnvelopeController {

    @Autowired
    private EventPublisher eventPublisher;

    @PostMapping("/publish")
    public String publishEvent(@RequestBody EventEnvelope event) {
        eventPublisher.publishEvent(event);
        return "Event published to Redis stream!";
    }

    // Sample endpoint to test easily (optional)
    @GetMapping("/sample")
    public String publishSample() {
        EventEnvelope event = new EventEnvelope("USER_REGISTERED", new User("abc123", "Alice"));
        eventPublisher.publishEvent(event);
        return "Sample event published.";
    }
}