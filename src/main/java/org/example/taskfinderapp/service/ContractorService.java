package org.example.taskfinderapp.service;

import org.example.taskfinderapp.model.Contractor;
import org.example.taskfinderapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractorService {
    private final CustomerRepository customerRepository;

    @Autowired
    public ContractorService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Contractor createContractor(String firstName, String lastName) {
        return null;
    }

}
