package com.example.testWork.dtos;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public class RegistrationEventDTO extends DefaultEventDTO{

    @NotNull(message = "clientId should not be empty")
    private Integer clientId;

    @NotNull(message = "productId should not be empty")
    private Integer productId;

    @NotNull(message = "productCost should not be empty")
    private Double productCost;

    @NotBlank(message = "expectedPickUPTime should not be empty")
    private String expectedPickUPTime;

    public RegistrationEventDTO() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getProductCost() {
        return productCost;
    }

    public void setProductCost(Double productCost) {
        this.productCost = productCost;
    }

    public String getExpectedPickUPTime() {
        return expectedPickUPTime;
    }

    public void setExpectedPickUPTime(String expectedPickUPTime) {
        this.expectedPickUPTime = expectedPickUPTime;
    }

}
