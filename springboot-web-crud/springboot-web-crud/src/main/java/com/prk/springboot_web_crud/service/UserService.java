package com.prk.springboot_web_crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prk.springboot_web_crud.model.User;
import com.prk.springboot_web_crud.Repository.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // CREATE
    public User saveUser(User user) {
        return repo.save(user);
    }

    // READ ALL
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // READ ONE
    public User getUserById(int rollno) {
        return repo.findById(rollno).orElse(null);
    }

    // UPDATE
    public User updateUser(User user) {
        return repo.save(user);
    }

    // DELETE
    public void deleteUser(int rollno) {
        repo.deleteById(rollno);
    }
}