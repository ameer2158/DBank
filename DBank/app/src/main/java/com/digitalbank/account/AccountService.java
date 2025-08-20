package com.digitalbank.account;

import com.digitalbank.api.dto.OpenAccountRequest;
import com.digitalbank.api.dto.AccountResponse;
import com.digitalbank.customer.BusinessCustomer;
import com.digitalbank.customer.CustomerRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepo;
    private final CustomerRepository customerRepo;

    public AccountService(AccountRepository accountRepo, CustomerRepository customerRepo) {
        this.accountRepo = accountRepo;
        this.customerRepo = customerRepo;
    }


    public AccountResponse open(OpenAccountRequest req) {
        var customer = customerRepo.findById(req.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        if (!accountRepo.findByCustomer(customer.getId()).isEmpty()){
            throw new DuplicateRequestException("Account already exist for customer");
        }

        Account a = new Account(customer);
        accountRepo.save(a);
        return toResponse(a);
    }

    public AccountResponse get(String id) {
        return accountRepo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    public List<AccountResponse> list() {
        return accountRepo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<AccountResponse> listByCustomer(String customerId) {
        return accountRepo.findByCustomer(customerId).stream().map(this::toResponse).collect(Collectors.toList());
    }

    private AccountResponse toResponse(Account a) {
        AccountResponse r = new AccountResponse();
        r.setId(a.getId());
        customerRepo.findById(a.getCustomerId()).ifPresent(c -> {
            AccountResponse.CustomerBrief b = new AccountResponse.CustomerBrief();
            b.setId(c.getId());
            b.setName(c.getName());
            b.setType(c.getType());
            if (c instanceof BusinessCustomer bc && bc.getBusinessNumber() != null){
                b.setBusinessNumber(bc.getBusinessNumber());
            } else {
                b.setPersonalId(c.getPersonal_id());
            }
            r.setCustomer(b);
        });

        return r;
    }
}