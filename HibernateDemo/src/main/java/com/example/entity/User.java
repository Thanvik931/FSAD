package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "rollno")
    private int rollno;
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    // Default constructor (required by Hibernate)
    public User() {
    }
    
    // Parameterized constructor
    public User(int rollno, String name) {
        this.rollno = rollno;
        this.name = name;
    }
    
    // Getters and Setters
    public int getRollno() {
        return rollno;
    }
    
    public void setRollno(int rollno) {
        this.rollno = rollno;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "User [rollno=" + rollno + ", name=" + name + "]";
    }
}