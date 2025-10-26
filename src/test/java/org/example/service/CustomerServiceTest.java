package org.example.service;

import org.example.taskfinderapp.model.Customer;
import org.example.taskfinderapp.model.Task;
import org.example.taskfinderapp.repository.CustomerRepository;
import org.example.taskfinderapp.service.CustomerService;
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
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() {
        String firstName = "Cust";
        String lastName = "Omer";
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer createdCustomer = customerService.createCustomer(firstName, lastName);
        assertNotNull(createdCustomer);
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
    }
}
