package com.prk.userapp.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prk.userapp.model.User;
import com.prk.userapp.repository.UserRepository;
@Service
public class UserService{
@Autowired
private UserRepository repo;
public User saveUser(User user){
return repo.save(user);
}
public List<User> fetchFilteredUsers(){
return repo.findByRollnoGreaterThanEqualAndNameStartingWith(10,"P");
}
}