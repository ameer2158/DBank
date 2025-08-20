package com.digitalbank.transaction;

import com.digitalbank.account.Account;
import com.digitalbank.account.AccountRepository;
import com.digitalbank.api.dto.AccountResponse;
import com.digitalbank.api.dto.TransactionRequest;
import com.digitalbank.api.dto.TransactionResponse;
import com.digitalbank.customer.BusinessCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private final TransactionRepository txRepo;

    @Autowired
    private final AccountRepository accountRepo;

    private final KafkaTransactionProducer producer;

    private final Object publishLock = new Object();

    public TransactionService(TransactionRepository txRepo, AccountRepository accountRepo, KafkaTransactionProducer producer) {
        this.txRepo = txRepo;
        this.accountRepo = accountRepo;
        this.producer = producer;
    }

    public TransactionResponse create(TransactionRequest req) {
        if (req.getAmount() <= 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        Account from = null, to = null;
        if (req.getEvent_type().equals("TRANSACTION")) {
            from = accountRepo.findById(req.getFrom_account())
                    .orElseThrow(() -> new IllegalArgumentException("from account not found"));
            to = accountRepo.findById(req.getTo_account())
                    .orElseThrow(() -> new IllegalArgumentException("to account not found"));
        } else {
            throw new IllegalArgumentException("Unsupported event type");
        }

        Transaction t = new Transaction(
                req.getEvent_type(),
                from != null ? from.getId() : null,
                to   != null ? to.getId()   : null,
                req.getAmount(),
                req.getCurrency(),
                req.getDescription()
        );
        txRepo.save(t);

        synchronized (publishLock){
            if ("TRANSACTION".equalsIgnoreCase(t.getEvent_type())) {
                TransactionEvent ev = new TransactionEvent(
                        t.getEvent_type(),
                        t.getTimestamp(),
                        t.getTransaction_id(),
                        t.getFrom_account_id(),
                        t.getTo_account_id(),
                        t.getAmount(),
                        t.getCurrency(),
                        t.getDescription()
                );
                producer.publish(ev);
            }
        }

        return toResponse(t, from, to);
    }

    public TransactionResponse get(String id) {
        Transaction t = txRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        Account from = t.getFrom_account_id() != null ? accountRepo.findById(t.getFrom_account_id()).orElse(null) : null;
        Account to   = t.getTo_account_id()   != null ? accountRepo.findById(t.getTo_account_id()).orElse(null)   : null;
        return toResponse(t, from, to);
    }

    public List<TransactionResponse> list() {
        return txRepo.findAll().stream().map(t -> {
            Account from = t.getFrom_account_id() != null ? accountRepo.findById(t.getFrom_account_id()).orElse(null) : null;
            Account to   = t.getTo_account_id()   != null ? accountRepo.findById(t.getTo_account_id()).orElse(null)   : null;
            return toResponse(t, from, to);
        }).collect(Collectors.toList());
    }

    public List<TransactionResponse> listByAccount(String accountId) {
        return txRepo.findByAccount(accountId).stream().map(t -> {
            Account from = t.getFrom_account_id() != null ? accountRepo.findById(t.getFrom_account_id()).orElse(null) : null;
            Account to   = t.getTo_account_id()   != null ? accountRepo.findById(t.getTo_account_id()).orElse(null)   : null;
            return toResponse(t, from, to);
        }).collect(Collectors.toList());
    }

    private TransactionResponse toResponse(Transaction t, Account from, Account to) {
        TransactionResponse r = new TransactionResponse();
        r.setTransaction_id(t.getTransaction_id());
        r.setEvent_type(t.getEvent_type());
        r.setTimestamp(t.getTimestamp());
        r.setAmount(t.getAmount());
        r.setCurrency(t.getCurrency());
        r.setDescription(t.getDescription());

        if (from != null) {
            TransactionResponse.AccountBrief fb = new TransactionResponse.AccountBrief();
            fb.setId(from.getId());
            fb.setBalanceBefore(from.getBalance());
            if (from.getBalance() - t.getAmount() < 0) {
                throw new RuntimeException("");
            }
            from.setBalance(from.getBalance() - t.getAmount());
            fb.setBalanceAfter(from.getBalance());

            TransactionResponse.AccountBrief.CustomerBrief ab = getCustomerBrief(from);
            fb.setCustomer(ab);
            r.setFrom_account(fb);
        }
        if (to != null) {
            TransactionResponse.AccountBrief fb = new TransactionResponse.AccountBrief();
            fb.setId(to.getId());
            fb.setBalanceBefore(to.getBalance());
            to.setBalance(to.getBalance() + t.getAmount());
            fb.setBalanceAfter(to.getBalance());

            TransactionResponse.AccountBrief.CustomerBrief ab = getCustomerBrief(to);
            fb.setCustomer(ab);
            r.setTo_account(fb);
        }

        TransactionResponse.MetaData mt = new TransactionResponse.MetaData("bank-core-service", "api/v1/transfer");
        r.setMetaData(mt);
        return r;
    }

    private static TransactionResponse.AccountBrief.CustomerBrief getCustomerBrief(Account from) {
        TransactionResponse.AccountBrief.CustomerBrief ab = new TransactionResponse.AccountBrief.CustomerBrief();
        ab.setId(from.getCustomer().getId());
        ab.setName(from.getCustomer().getName());
        ab.setType(from.getCustomer().getType());
        if (from.getCustomer() instanceof BusinessCustomer bc && bc.getBusinessNumber() != null){
            ab.setBusinessNumber(bc.getBusinessNumber());
        } else {
            ab.setPersonalId(from.getCustomer().getPersonal_id());
        }
        return ab;
    }
}
