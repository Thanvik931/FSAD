package com.prk.userapp.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.prk.userapp.model.User;
import com.prk.userapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController{
@Autowired
private UserService service;
@PostMapping("/add")
public User addUser(@RequestBody User user){
return service.saveUser(user);
}
@GetMapping("/filtered")
public List<User>getFilteredUsers(){
return service.fetchFilteredUsers();
}
}