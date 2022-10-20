package com.example.testWork.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

public class RegistrationData extends EventData{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz", timezone = "UTC")
    @NotEmpty(message = "expectedPickUPTime should not be empty")
    //@JsonDeserialize(using = InstantDeserializer.class)
    private Instant expectedPickUPTime;

    public RegistrationData() {
    }

    public Instant getExpectedPickUPTime() {
        return expectedPickUPTime;
    }

    public void setExpectedPickUPTime(Instant expectedPickUPTime) {
        this.expectedPickUPTime = expectedPickUPTime;
    }

    @Override
    public String toString() {
        return "{\"expectedPickUPTime\": " + "\"" + expectedPickUPTime + "\"}";
    }

}
