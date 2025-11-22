package org.example.model;

import org.example.taskfinderapp.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TaskTest {

    @Test
    public void testIsOngoing() {
        Task task = new Task();
        task.setStatus("Open");
        assertTrue(task.isOngoing());
        task.setStatus("Accepted");
        assertTrue(task.isOngoing());
        task.setStatus("Completed");
        assertTrue(task.isOngoing());
        task.setStatus("Closed");
        assertFalse(task.isOngoing());
        task.setStatus("Cancelled");
        assertFalse(task.isOngoing());
    }
}
