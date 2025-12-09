package org.example.taskfinderapp.repository;

import org.example.taskfinderapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="tasks")
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCustomerId(Long customerId);
    List<Task> findByContractorId(Long contractorId);
    List<Task> findByStatus(String status);
}
