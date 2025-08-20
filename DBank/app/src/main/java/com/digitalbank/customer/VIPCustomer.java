package com.digitalbank.customer;

public class VIPCustomer extends Customer {

    public VIPCustomer(String personal_id, String name) {
        super(personal_id, name, CustomerType.VIP);
    }
}