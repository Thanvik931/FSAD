package com.prk.webcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prk.webcrud.model.User;
import com.prk.webcrud.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    // Show all users
    @GetMapping("/")
    public String viewUsers(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    // Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    // Save user
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        service.saveUser(user);
        return "redirect:/";
    }

    // Delete user
    @GetMapping("/delete/{rollno}")
    public String deleteUser(@PathVariable int rollno) {
        service.deleteUser(rollno);
        return "redirect:/";
    }
}