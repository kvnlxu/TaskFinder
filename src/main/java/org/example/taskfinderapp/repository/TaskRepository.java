package org.example.taskfinderapp.repository;

import org.example.taskfinderapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCustomerId(Long customerId);
    List<Task> findByContractorId(Long contractorId);
    List<Task> findByStatus(String status);
}
