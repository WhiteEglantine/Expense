package com.example.expense.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Converter
public class JsonSetConverter implements AttributeConverter<Set<String>, String> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public JsonSetConverter() {
    }

    public String convertToDatabaseColumn(Set<String> data) {
        if (null == data) {
            return null;
        } else {
            try {
                return mapper.writeValueAsString(data);
            } catch (IOException var3) {
                throw new IllegalArgumentException("Error converting Set to JSON", var3);
            }
        }
    }

    public Set<String> convertToEntityAttribute(String s) {
        if (null == s) {
            return new HashSet<>();
        } else {
            try {
                return mapper.readValue(s, new TypeReference<Set<String>>() {
                });
            } catch (IOException var3) {
                throw new IllegalArgumentException("Error converting JSON to Set", var3);
            }
        }
    }
}
