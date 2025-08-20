package com.digitalbank.transaction;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransactionRepository {
    private final Map<String, Transaction> store = new ConcurrentHashMap<>();

    public Transaction save(Transaction t) {
        store.put(t.getTransaction_id(), t);
        return t;
    }

    public Optional<Transaction> findById(String id) { return Optional.ofNullable(store.get(id)); }

    public List<Transaction> findAll() { return new ArrayList<>(store.values()); }

    public List<Transaction> findByAccount(String accountId) {
        return store.values().stream()
                .filter(t -> accountId.equals(t.getFrom_account_id()) || accountId.equals(t.getTo_account_id()))
                .toList();
    }
}