package com.prk.userapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prk.userapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByRollnoGreaterThanEqualAndNameStartingWith(int rollno, String name);
}