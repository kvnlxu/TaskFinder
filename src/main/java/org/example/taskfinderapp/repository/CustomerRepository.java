package org.example.taskfinderapp.repository;

import org.example.taskfinderapp.model.Customer;
import org.example.taskfinderapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
