package com.digitalbank.api.dto;

import com.digitalbank.customer.Customer;
import com.digitalbank.customer.CustomerType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
    private String id;
    private CustomerBrief customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerBrief getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBrief customer) {
        this.customer = customer;
    }

    public void setBalance(double balance) {

    }

    public static class CustomerBrief {
        private String id;
        private String name;
        private CustomerType type;
        private String personalId;
        private String businessNumber;

        public String getName() {
            return name;
        }

        public CustomerType getType() {
            return type;
        }

        public void setType(CustomerType type) {
            this.type = type;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPersonalId() {
            return personalId;
        }

        public void setPersonalId(String personalId) {
            this.personalId = personalId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBusinessNumber() {
            return businessNumber;
        }

        public void setBusinessNumber(String businessNumber){
            this.businessNumber = businessNumber;
        }
    }
}