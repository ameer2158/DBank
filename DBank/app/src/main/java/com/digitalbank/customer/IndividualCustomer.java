package com.digitalbank.customer;

public class IndividualCustomer extends Customer {

    public IndividualCustomer(String personal_id, String name) {
        super(personal_id, name, CustomerType.INDIVIDUAL);
    }

}