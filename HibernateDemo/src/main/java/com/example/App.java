package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.entity.User;
import com.example.util.HibernateUtil;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting Hibernate MySQL Demo...");
        
        // Insert new user
        insertUser(1, "P R Kumar");
        insertUser(4, "Ch Ramesh");
        
        // Retrieve and display users
        getAllUsers();
        
        // Update a user
        updateUser(6, "Sudh+++++++++++a R");
        
        // Delete a user
        deleteUser(4);
        
        // Display final list
        getAllUsers();
        
        // Close Hibernate
        HibernateUtil.shutdown();
        System.out.println("Program completed successfully!");
    }
    
    private static void insertUser(int rollno, String name) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            User user = new User(rollno, name);
            session.save(user);
            
            transaction.commit();
            System.out.println("User inserted: " + name);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    private static void getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("\n=== All Users in Database ===");
            session.createQuery("from User", User.class)
                   .list()
                   .forEach(user -> System.out.println(user));
            System.out.println("==============================\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void updateUser(int rollno, String newName) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            User user = session.get(User.class, rollno);
            if (user != null) {
                user.setName(newName);
                session.update(user);
                transaction.commit();
                System.out.println("User updated: Roll No " + rollno + " -> " + newName);
            } else {
                System.out.println("User not found with rollno: " + rollno);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    private static void deleteUser(int rollno) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            User user = session.get(User.class, rollno);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                System.out.println("User deleted: Roll No " + rollno);
            } else {
                System.out.println("User not found with rollno: " + rollno);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}