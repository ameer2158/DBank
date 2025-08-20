package com.digitalbank.api;

import com.digitalbank.api.dto.OpenAccountRequest;
import com.digitalbank.api.dto.AccountResponse;
import com.digitalbank.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> open(@Valid @RequestBody OpenAccountRequest req) {
        AccountResponse created = service.open(req);
        return ResponseEntity.created(URI.create("/api/accounts/" + created.getId())).body(created);
    }

    @GetMapping
    public List<AccountResponse> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable("id") String id) {
        return service.get(id);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<AccountResponse> byCustomer(@PathVariable("customerId") String customerId) {
        return service.listByCustomer(customerId);
    }
}