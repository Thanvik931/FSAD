package com.prk.springboot_web_crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prk.springboot_web_crud.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}