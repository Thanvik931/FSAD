package com.prk.webcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prk.webcrud.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}