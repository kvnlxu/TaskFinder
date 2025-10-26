package org.example.taskfinderapp.repository;

import org.example.taskfinderapp.model.Contractor;
import org.example.taskfinderapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
}
