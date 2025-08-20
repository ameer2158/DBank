package com.digitalbank.customer;

public class BusinessCustomer extends Customer {
    private String businessNumber;

    public BusinessCustomer(String personal_id, String name, String businessNumber) {
        super(personal_id, name, CustomerType.BUSINESS);
        this.businessNumber = businessNumber;
    }

    public String getBusinessNumber() { return businessNumber; }
    public void setBusinessNumber(String businessNumber) { this.businessNumber = businessNumber; }
}