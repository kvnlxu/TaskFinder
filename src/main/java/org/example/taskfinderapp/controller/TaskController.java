package org.example.taskfinderapp.controller;

import org.example.taskfinderapp.model.AppUser;
import org.example.taskfinderapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.taskfinderapp.service.TaskService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("tasks")
    public String tasksView(Model model, @AuthenticationPrincipal AppUser appUser) {
        model.addAttribute("userRole", appUser.getRole());
        if(appUser.getRole().equals("CUSTOMER")) {
            List<Task> customerTasks = taskService.getTasksByCustomerId(appUser.getId());
            List<Task> ongoingTasks = new ArrayList<>();
            List<Task> completedTasks = new ArrayList<>();
            List<Task> cancelledTasks  = new ArrayList<>();
            for (Task task : customerTasks) {
                if (task.isOngoing()) {
                    ongoingTasks.add(task);
                } else if (task.getStatus().equals("Approved")) {
                    completedTasks.add(task);
                } else if (task.getStatus().equals("Cancelled")) {
                    cancelledTasks.add(task);
                }
            }
            model.addAttribute("ongoingTasks", ongoingTasks);
            model.addAttribute("completedTasks", completedTasks);
            model.addAttribute("cancelledTasks", cancelledTasks);
        } else if(appUser.getRole().equals("CONTRACTOR")) {
            List<Task> contractorTask = taskService.getTasksByContractorId(appUser.getId());
            List<Task> openTasks = taskService.getTasksByStatus("Open");
            List<Task> acceptedTasks = new ArrayList<>();
            List<Task> completedTasks = new ArrayList<>();
            for (Task task : contractorTask) {
                if (task.isOngoing()) {
                    acceptedTasks.add(task);
                } else if (task.getStatus().equals("Approved")) {
                    completedTasks.add(task);
                }
            }
            model.addAttribute("openTasks", openTasks);
            model.addAttribute("acceptedTasks", acceptedTasks);
            model.addAttribute("completedTasks", completedTasks);
        }
        return "tasks";
    }

    @GetMapping("tasks/{id}")
    public String taskView(Model model, @PathVariable int id, @AuthenticationPrincipal AppUser user) {
        Task task = taskService.getTask(id);
        String userRole = user.getRole();
        long userId = user.getId();
        model.addAttribute("task", task);
        model.addAttribute("userRole", userRole);
        model.addAttribute("userId", userId);
        return "task";
    }

    @GetMapping("taskform")
    public String taskFormView(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping("task")
    public String taskSubmit(@ModelAttribute Task task, @AuthenticationPrincipal AppUser user) {
        taskService.createTask(user.getId(), task.getTitle(), task.getDescription(), task.getPrice());
        return "redirect:/tasks";
    }

    @PostMapping("accepttask")
    public String acceptTask(@ModelAttribute Task task, @AuthenticationPrincipal AppUser user) {
        taskService.acceptTask(user.getId(), task.getId());
        return "redirect:/tasks/" + task.getId();
    }

    @PostMapping("completetask")
    public String completeTask(@ModelAttribute Task task) {
        taskService.completeTask(task.getId());
        return "redirect:/tasks/" + task.getId();
    }

    @PostMapping("approvetask")
    public String approveTask(@ModelAttribute Task task) {
        taskService.approveTask(task.getId());
        return "redirect:/tasks/" + task.getId();
    }

    @PostMapping("reopentask")
    public String reopenTask(@ModelAttribute Task task) {
        taskService.cancelTask(task.getId());
        taskService.openTask(task.getId());
        return "redirect:/tasks/" + task.getId();
    }

    @PostMapping("canceltask")
    public String cancelTask(@ModelAttribute Task task) {
        taskService.cancelTask(task.getId());
        return "redirect:/tasks/" + task.getId();
    }

}
