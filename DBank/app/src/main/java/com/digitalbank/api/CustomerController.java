package com.digitalbank.api;

import com.digitalbank.api.dto.CreateCustomerRequest;
import com.digitalbank.api.dto.CustomerResponse;
import com.digitalbank.customer.CustomerService;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CreateCustomerRequest req){
        CustomerResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/customers/" + created.getId())).body(created);
    }

    @GetMapping
    public List<CustomerResponse> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public CustomerResponse get(@PathVariable("id") String id) {
        return service.get(id);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

}
