package com.digitalbank.api.dto;

import com.digitalbank.customer.CustomerType;
import jakarta.validation.constraints.*;

public class CreateCustomerRequest {
    @NotBlank(message = "name must not be empty")
    private String name;

    @NotNull
    private CustomerType type;

    private String personal_id;

    private String businessNumber;

    public String getName() {
        return name;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

}