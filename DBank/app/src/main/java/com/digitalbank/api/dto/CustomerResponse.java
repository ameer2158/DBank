package com.digitalbank.api.dto;

import com.digitalbank.customer.CustomerType;

public class CustomerResponse {
    private String id;
    private String name;
    private CustomerType type;

    private String businessNumber;
    private String personal_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }

    public String getName() {
        return name;
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