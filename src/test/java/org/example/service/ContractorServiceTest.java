package org.example.service;

import org.example.taskfinderapp.model.Contractor;
import org.example.taskfinderapp.repository.ContractorRepository;
import org.example.taskfinderapp.service.ContractorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ContractorServiceTest {
    @Mock
    ContractorRepository contractorRepository;
    @InjectMocks
    ContractorService contractorService;

    @Test
    public void testAddContractor() {
        String firstName = "Con";
        String lastName = "Tractor";
        Contractor contractor = new Contractor();
        contractor.setFirstName(firstName);
        contractor.setLastName(lastName);
        Mockito.when(contractorRepository.save(any(Contractor.class))).thenReturn(contractor);
        Contractor createdContractor = contractorService.createContractor(firstName, lastName);
        assertNotNull(createdContractor);
        assertEquals(firstName, contractor.getFirstName());
        assertEquals(lastName, contractor.getLastName());
    }
}
