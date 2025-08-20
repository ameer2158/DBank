package com.digitalbank.customer;

import java.util.UUID;

public abstract class Customer {
    private final String id;
    private String personal_id;
    private String name;
    private final CustomerType type;

    protected Customer(String personal_id, String name, CustomerType type) {
        this.id = UUID.randomUUID().toString();
        this.personal_id = personal_id;
        this.name = name;
        this.type = type;
    }

    protected Customer(String name, CustomerType type) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public String getId() {
        return id;
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

}