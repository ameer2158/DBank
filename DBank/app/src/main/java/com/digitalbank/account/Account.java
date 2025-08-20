package com.digitalbank.account;

import com.digitalbank.customer.Customer;

import java.util.UUID;

public class Account {
    private final String id;
    private final Customer customer;
    private double balance;

    public Account(Customer customer) {
        this.customer = customer;
        this.id = UUID.randomUUID().toString();
        this.balance = 1500;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerId(){
        return customer.getId();
    }

}

