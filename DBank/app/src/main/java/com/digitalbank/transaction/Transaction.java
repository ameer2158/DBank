package com.digitalbank.transaction;

import java.time.Instant;
import java.util.UUID;

public class Transaction {
    private final String transaction_id;
    private final String event_type;
    private final String timestamp;
    private final String from_account_id;
    private final String to_account_id;
    private final double amount;
    private final String currency;
    private final String description;

    public Transaction(String event_type, String from_account_id, String to_account_id, double amount, String currency, String description) {
        this.transaction_id = UUID.randomUUID().toString();
        this.event_type = event_type;
        this.timestamp = Instant.now().toString();
        this.from_account_id = from_account_id;
        this.to_account_id = to_account_id;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getEvent_type() {
        return event_type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getFrom_account_id() {
        return from_account_id;
    }

    public String getTo_account_id() {
        return to_account_id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }
}