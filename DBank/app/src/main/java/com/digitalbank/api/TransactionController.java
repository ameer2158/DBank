package com.digitalbank.api;

import com.digitalbank.api.dto.TransactionRequest;
import com.digitalbank.api.dto.TransactionResponse;
import com.digitalbank.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest req) {
        TransactionResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/transactions/" + created.getTransaction_id())).body(created);
    }

    @GetMapping
    public List<TransactionResponse> list() { return service.list(); }

    @GetMapping("/{id}")
    public TransactionResponse get(@PathVariable("id") String id) { return service.get(id); }

    @GetMapping("/by-account/{accountId}")
    public List<TransactionResponse> byAccount(@PathVariable("accountId") String accountId) {
        return service.listByAccount(accountId);
    }
}
