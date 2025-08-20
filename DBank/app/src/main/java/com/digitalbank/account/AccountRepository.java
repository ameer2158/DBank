package com.digitalbank.account;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {
    private final Map<String, Account> store = new ConcurrentHashMap<>();

    public Account save(Account a) {
        store.put(a.getId(), a);
        return a;
    }

    public Optional<Account> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Account> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<Account> findByCustomer(String customerId) {
        return store.values().stream().filter(a -> a.getCustomerId().equals(customerId)).toList();
    }
}