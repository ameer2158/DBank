package com.digitalbank.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionEvent {
    private String event_type;
    private String timestamp;
    private String transaction_id;
    private String from_account;
    private String to_account;
    private double amount;
    private String currency;
    private String description;

    public TransactionEvent() {}
    public TransactionEvent(String event_type, String timestamp, String transaction_id,
                            String from_account, String to_account,
                            double amount, String currency, String description) {
        this.event_type = event_type;
        this.timestamp = timestamp;
        this.transaction_id = transaction_id;
        this.from_account = from_account;
        this.to_account = to_account;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }
    public String getEvent_type() { return event_type; }
    public void setEvent_type(String event_type) { this.event_type = event_type; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getTransaction_id() { return transaction_id; }
    public void setTransaction_id(String transaction_id) { this.transaction_id = transaction_id; }
    public String getFrom_account() { return from_account; }
    public void setFrom_account(String from_account) { this.from_account = from_account; }
    public String getTo_account() { return to_account; }
    public void setTo_account(String to_account) { this.to_account = to_account; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}