package org.example.taskfinderapp.service;

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
        task.setStatus("Open");
        return taskRepository.save(task);
    }

    public void deleteTask(long taskId) {
        taskRepository.deleteById(taskId);
    }

    public List<Task> getTasksByCustomerId(long customerId) {
        return null;
    }

    public List<Task> getTasksByContractorId(long contractorId) {
        return null;
    }

    public Task acceptTask(long contractorId, long taskId)  {
        //set contractorId and change status to "Accepted"
        return null;
    }

    public Task completeTask(long taskId) {
        //set status to "Completed"
        return null;
    }

    public Task approveTask(long taskId) {
        //set task status to "Approved"
        return null;
    }

    public Task cancelTask(long taskId) {
        //set task status to "Cancelled"
        return null;
    }
}
