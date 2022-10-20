package com.example.testWork.support;

import com.example.testWork.models.EventData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;

public class ObjectConverter implements AttributeConverter<Object, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ObjectConverter.class);
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public String convertToDatabaseColumn(Object attributeObject) {
        if (attributeObject == null) {
            return "";
        }
        try {
            return mapper.writeValueAsString(attributeObject);
        } catch (JsonProcessingException e) {
            LOGGER.error("Could not convert to database column", e);
            return null;
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbColumnValue) {
        try {
            if (dbColumnValue == null || dbColumnValue.isBlank()) {
                return null;
            }
            return mapper.readValue(dbColumnValue, EventData.class); // ----> mapped to
        } catch (Exception e) {
            LOGGER.error("Could not convert to entity attribute", e);
            return null;
        }
    }
}