package org.example.taskfinderapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.taskfinderapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.taskfinderapp.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(long customerId, String title, String description, double price) {
        Task task = new Task();
        task.setCustomerId(customerId);
        task.setTitle(title);
        task.setDescription(description);
        task.setPrice(price);
        task.setStatus("Open");
        return taskRepository.save(task);
    }

    public void deleteTask(long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task getTask(long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("ID not found"));
    }

    public List<Task> getTasksByCustomerId(long customerId) {
        return taskRepository.findByCustomerId(customerId);
    }

    public List<Task> getTasksByContractorId(long contractorId) {
        return taskRepository.findByContractorId(contractorId);
    }

    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public Task openTask(long taskId) {
        Task task = getTask(taskId);
        task.setStatus("Open");
        return taskRepository.save(task);
    }

    public Task acceptTask(long contractorId, long taskId)  {
        Task task = getTask(taskId);
        task.setContractorId(contractorId);
        task.setStatus("Accepted");
        return taskRepository.save(task);
    }

    public Task completeTask(long taskId) {
        Task task = getTask(taskId);
        task.setStatus("Completed");
        return taskRepository.save(task);
    }

    public Task approveTask(long taskId) {
        Task task = getTask(taskId);
        task.setStatus("Approved");
        return taskRepository.save(task);
    }

    public Task cancelTask(long taskId) {
        Task task = getTask(taskId);
        task.setStatus("Cancelled");
        task.setContractorId(null);
        return taskRepository.save(task);
    }
}
