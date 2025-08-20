package com.digitalbank.api.dto;

import jakarta.validation.constraints.NotBlank;

public class OpenAccountRequest {
    @NotBlank
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}