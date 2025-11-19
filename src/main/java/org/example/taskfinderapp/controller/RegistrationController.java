package org.example.taskfinderapp.controller;

import org.example.taskfinderapp.model.AppUser;
import org.example.taskfinderapp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private AppUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") AppUser user) {
        userService.save(user);
        return "redirect:/register?success";
    }

    @PostMapping("/registercustomer")
    public String registerCustomerUser(@ModelAttribute("user") AppUser user) {
        try {
            user.setRole("CUSTOMER");
            userService.save(user);
            return "redirect:/register?success";
        } catch (Exception e) {
            return "redirect:/register?error";
        }
    }

    @PostMapping("/registercontractor")
    public String registerContractorUser(@ModelAttribute("user") AppUser user) {
        try {
            user.setRole("CONTRACTOR");
            userService.save(user);
            return "redirect:/register?success";
        } catch (Exception e) {
            return "redirect:/register?error";
        }
    }
}
