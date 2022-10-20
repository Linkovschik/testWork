package com.example.testWork.models;

public class CancellingData extends EventData {
    private String reason;

    public CancellingData() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "{\"reason\": " + "\"" + reason + "\"}";
    }
}
