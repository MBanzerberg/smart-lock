package com.smartlock.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.Deserializer;

import java.io.IOException;
import java.util.Map;

public class CustomJacksonDeserializer implements Deserializer<Map<String, ?>> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Map<String, ?> deserialize(byte[] data) throws io.jsonwebtoken.io.IOException {
        try {
            return objectMapper.readValue(data, Map.class);
        } catch (JsonProcessingException e) {
            throw new io.jsonwebtoken.io.IOException("Error deserializing data", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
