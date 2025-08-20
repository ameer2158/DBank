package com.digitalbank.api.dto;

import com.digitalbank.customer.CustomerType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {
    private String event_type;
    private String timestamp;
    private String transaction_id;
    private AccountBrief from_account;
    private AccountBrief to_account;
    private double amount;
    private String currency;
    private String description;
    private MetaData metaData;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public AccountBrief getFrom_account() {
        return from_account;
    }

    public void setFrom_account(AccountBrief from_account) {
        this.from_account = from_account;
    }

    public AccountBrief getTo_account() {
        return to_account;
    }

    public void setTo_account(AccountBrief to_account) {
        this.to_account = to_account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MetaData getMetaData(){
        return metaData;
    }

    public void setMetaData(MetaData metaData){
        this.metaData = metaData;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AccountBrief {
        private String id;
        private double balanceBefore;
        private double balanceAfter;
        private CustomerBrief customer;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getBalanceBefore() {
            return balanceBefore;
        }

        public void setBalanceBefore(double balanceBefore) {
            this.balanceBefore = balanceBefore;
        }

        public double getBalanceAfter() {
            return balanceAfter;
        }

        public void setBalanceAfter(double balanceAfter) {
            this.balanceAfter = balanceAfter;
        }

        public CustomerBrief getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBrief customer) {
            this.customer = customer;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MetaData {
        private String processed_by;
        private String source;

        public MetaData(String processed_by, String source) {
            this.processed_by = processed_by;
            this.source = source;
        }

        public String getProcessed_by() {
            return processed_by;
        }

        public void setProcessed_by(String processed_by) {
            this.processed_by = processed_by;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

}