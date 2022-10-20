package com.example.testWork.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS,
        include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegistrationData.class, name = "Registered"),
        @JsonSubTypes.Type(value = CancellingData.class, name = "Cancelled")
})
public class EventData {

    public EventData() {
    }

    @Override
    public String toString() {
        return "{}";
    }
}
