package org.example.service;

import org.example.taskfinderapp.model.Task;
import org.example.taskfinderapp.repository.TaskRepository;
import org.example.taskfinderapp.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        long customerId = 1;
        String title = "test title";
        String description = "test description";
        double price = 50.0;
        Task task = new Task();
        task.setCustomerId(customerId);
        task.setTitle(title);
        task.setDescription(description);
        task.setPrice(price);
        task.setStatus("Open");
        Mockito.when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task createdTask = taskService.createTask(customerId, title, description, price);
        assertNotNull(createdTask);
        assertEquals(customerId, createdTask.getCustomerId());
        assertEquals(title, createdTask.getTitle());
        assertEquals(description, createdTask.getDescription());
        assertEquals(price, createdTask.getPrice());
        assertEquals("Open", createdTask.getStatus());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task();
        task.setId(1L);
        Mockito.doNothing().when(taskRepository).deleteById(task.getId());
        taskService.deleteTask(task.getId());
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(task.getId());
    }

    @Test
    public void testGetTaskByCustomerId() {
        Long customerId = 1L;
        Task task1 = new Task();
        task1.setCustomerId(customerId);
        task1.setTitle("title1");
        Task task2 = new Task();
        task2.setCustomerId(customerId);
        task2.setTitle("title2");
        List<Task> tasks = Arrays.asList(
                task1,
                task2
        );
        Mockito.when(taskRepository.findByCustomerId(customerId)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getTasksByCustomerId(customerId);
        assertNotNull(foundTasks);
        assertEquals(tasks.size(), foundTasks.size());
        assertEquals(task1.getTitle(), foundTasks.get(0).getTitle());
        assertEquals(task2.getTitle(), foundTasks.get(1).getTitle());
    }

    @Test
    public void testGetTaskByCustomerIdWhenNotFound() {
        Long customerId = 1L;
        Mockito.when(taskRepository.findByCustomerId(customerId)).thenReturn(new ArrayList<Task>());
        List<Task> foundTasks = taskService.getTasksByCustomerId(customerId);
        assertNotNull(foundTasks);
        assertEquals(0, foundTasks.size());
    }

    @Test
    public void testGetTaskByContractorId() {
        Long contractorId = 1L;
        Task task1 = new Task();
        task1.setContractorId(contractorId);
        task1.setTitle("title1");
        Task task2 = new Task();
        task2.setContractorId(contractorId);
        task2.setTitle("title2");
        List<Task> tasks = Arrays.asList(
                task1,
                task2
        );
        Mockito.when(taskRepository.findByContractorId(contractorId)).thenReturn(tasks);

        List<Task> foundTasks = taskService.getTasksByContractorId(contractorId);

        assertNotNull(foundTasks);
        assertEquals(tasks.size(), foundTasks.size());
        assertEquals(task1.getTitle(), foundTasks.get(0).getTitle());
        assertEquals(task2.getTitle(), foundTasks.get(1).getTitle());
    }

    @Test
    public void testGetTaskByContractorIdWhenNotFound() {
        Long contractorId = 1L;
        Mockito.when(taskRepository.findByContractorId(contractorId)).thenReturn(null);
        List<Task> foundTasks = taskService.getTasksByContractorId(contractorId);
        assertNotNull(foundTasks);
        assertEquals(0, foundTasks.size());
    }

    @Test
    public void testAcceptTask() {
        long id = 1L;
        String title = "test title";
        String description = "test description";
        double price = 50.0;
        long customerId = 10L;
        long contractorId = 100L;
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setPrice(price);
        task.setStatus("Open");

        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        Task updatedTask = taskService.acceptTask(contractorId, id);
        assertNotNull(updatedTask);
        assertEquals("Accepted", updatedTask.getStatus());
    }

    @Test
    public void testCompleteTask() {
        long id = 1L;
        String title = "test title";
        String description = "test description";
        double price = 50.0;
        long customerId = 10L;
        long contractorId = 100L;
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setPrice(price);
        task.setStatus("Open");

        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        Task updatedTask = taskService.completeTask(id);
        assertNotNull(updatedTask);
        assertEquals("Completed", updatedTask.getStatus());
    }

    @Test
    public void testApproveTask() {
        long id = 1L;
        String title = "test title";
        String description = "test description";
        double price = 50.0;
        long customerId = 10L;
        long contractorId = 100L;
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setPrice(price);
        task.setStatus("Open");

        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        Task updatedTask = taskService.approveTask(id);
        assertNotNull(updatedTask);
        assertEquals("Approved", updatedTask.getStatus());
    }

    @Test
    public void testCancelTask() {
        long id = 1L;
        String title = "test title";
        String description = "test description";
        double price = 50.0;
        long customerId = 10L;
        long contractorId = 100L;
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setPrice(price);
        task.setStatus("Open");

        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        Task updatedTask = taskService.cancelTask(id);
        assertNotNull(updatedTask);
        assertEquals("Cancelled", updatedTask.getStatus());
    }
}
