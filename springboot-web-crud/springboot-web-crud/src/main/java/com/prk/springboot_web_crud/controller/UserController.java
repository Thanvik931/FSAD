package com.prk.springboot_web_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prk.springboot_web_crud.model.User;
import com.prk.springboot_web_crud.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // CREATE (POST)
    @PostMapping
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    // READ ALL (GET)
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // READ ONE (GET)
    @GetMapping("/{rollno}")
    public User getUser(@PathVariable int rollno) {
        return service.getUserById(rollno);
    }

    // UPDATE (PUT)
    @PutMapping
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    // DELETE (DELETE)
    @DeleteMapping("/{rollno}")
    public String deleteUser(@PathVariable int rollno) {
        service.deleteUser(rollno);
        return "User deleted with rollno " + rollno;
    }
}