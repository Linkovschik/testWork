package com.example.testWork.dtos;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public class CancelEventDTO extends DefaultEventDTO {
    @NotBlank(message = "reason should not be empty")
    private String reason;

    public CancelEventDTO() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
