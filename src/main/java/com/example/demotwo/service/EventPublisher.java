package com.example.demotwo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demotwo.dto.EventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.streams.Producer;

@Service
public class EventPublisher {

    private final Producer producer;
    private final ObjectMapper objectMapper;

    public EventPublisher(Producer producer, ObjectMapper objectMapper) {
        this.producer = producer;
        this.objectMapper = objectMapper;
    }

    public void publishEvent(EventEnvelope envelope) {
        try {
            String json = objectMapper.writeValueAsString(envelope);
            Map<String, String> msg = Map.of("payload", json);
            producer.produce(msg);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize EventEnvelope", e);
        }
    }
}




