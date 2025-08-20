package com.digitalbank.customer;

import com.digitalbank.api.dto.CreateCustomerRequest;
import com.digitalbank.api.dto.CustomerResponse;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public CustomerResponse create(CreateCustomerRequest req){
        Customer entity;
        switch (req.getType()){
            case INDIVIDUAL -> {
                entity = new IndividualCustomer(req.getPersonal_id(), req.getName());
            }
            case BUSINESS -> {
                if(!StringUtils.hasText(req.getBusinessNumber())){
                    throw new IllegalArgumentException("businessNumber is required for BUSINESS Customer Type!");
                }
                entity = new BusinessCustomer(req.getPersonal_id(), req.getName(), req.getBusinessNumber());
            }
            case VIP -> {
                entity = new VIPCustomer(req.getPersonal_id(), req.getName());
            }
            default -> throw new IllegalArgumentException("Unsupported type!");
        }
        if (repo.isPersonalIdExist(entity.getPersonal_id())){
            throw new DuplicateRequestException("Customer is already Exist");
        }
        repo.save(entity);
        return toResponse(entity);
    }

    private CustomerResponse toResponse(Customer c) {
        CustomerResponse response = new CustomerResponse();

        response.setId(c.getId());
        response.setName(c.getName());
        response.setType(c.getType());

        if (c instanceof BusinessCustomer bc && bc.getBusinessNumber() != null){
            response.setBusinessNumber(bc.getBusinessNumber());
        } else {
            response.setPersonal_id(c.getPersonal_id());
        }

        return response;
    }

    public List<CustomerResponse> list() {
        return repo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CustomerResponse get(String id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

}
