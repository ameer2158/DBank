package com.digitalbank.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {

    private final Map<String, Customer> store = new ConcurrentHashMap<>();
    private final Map<String, Customer> personalIdStore = new ConcurrentHashMap<>();

    public Customer save(Customer c){
        store.put(c.getId(), c);
        personalIdStore.put(c.getPersonal_id(), c);
        return c;
    }

    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public Boolean isPersonalIdExist(String personalId) {
        return personalId != null && personalIdStore.containsKey(personalId);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

}
