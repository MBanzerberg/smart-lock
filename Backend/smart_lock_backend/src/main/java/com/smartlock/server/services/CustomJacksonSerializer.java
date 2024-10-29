package com.smartlock.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.Serializer;

import java.util.Map;

public class CustomJacksonSerializer implements Serializer<Map<String, ?>> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(Map<String, ?> data) throws io.jsonwebtoken.io.IOException {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new io.jsonwebtoken.io.IOException("Error serializing data", e);
        }
    }
}
