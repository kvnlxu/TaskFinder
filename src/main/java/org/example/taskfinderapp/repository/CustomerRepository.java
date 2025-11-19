package org.example.taskfinderapp.repository;

import org.example.taskfinderapp.model.Customer;
import org.example.taskfinderapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
}
