package com.example.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.example.hibernate.model.User;
import com.example.hibernate.util.HibernateUtil;

public class UserDAO {
// Insert a new user
public void saveUser(User user) {
	Transaction transaction = null;
try (Session session = HibernateUtil.getSessionFactory().openSession()) {
transaction = session.beginTransaction();
session.persist(user);
transaction.commit();
System.out.println("User saved successfully: " + user);
} catch (Exception e) {
if (transaction != null) {
transaction.rollback();
}
e.printStackTrace();
}
}
// Insert multiple users
public void saveUsers(List<User> users) {
Transaction transaction = null;
try (Session session = HibernateUtil.getSessionFactory().openSession()) {
transaction = session.beginTransaction();
for (User user : users) {
session.persist(user);
}
transaction.commit();
System.out.println(users.size() + " users saved successfully!");
} catch (Exception e) {
if (transaction != null) {
transaction.rollback();
}
e.printStackTrace();
}
}
// HQL Query: Get all users with rollno >= 10 and name starting with 'P'
public List<User> getUsersWithRollnoAndName() {
try (Session session = HibernateUtil.getSessionFactory().openSession()) {
// HQL Query with named parameters
String hql = "FROM User u WHERE u.rollno >= :minRoll AND u.name LIKE :namePattern";
Query<User> query = session.createQuery(hql, User.class);
query.setParameter("minRoll", 10);
query.setParameter("namePattern", "P%");
List<User> users = query.list();
return users;
} catch (Exception e) {
e.printStackTrace();
return null;
}
}
// Get all users
public List<User> getAllUsers() {
try (Session session = HibernateUtil.getSessionFactory().openSession()) {
return session.createQuery("FROM User", User.class).list();
}
}
// Get user by rollno
public User getUserByRollno(int rollno) {
try (Session session = HibernateUtil.getSessionFactory().openSession()) {
return session.get(User.class, rollno);
}
}
// Update user
public void updateUser(User user) {
Transaction transaction = null;
try (Session session = HibernateUtil.getSessionFactory().openSession()) {
transaction = session.beginTransaction();
session.merge(user);
transaction.commit();
} catch (Exception e) {
if (transaction != null) {
	transaction.rollback();
	}
	e.printStackTrace();
	}
	}
	// Delete user
	public void deleteUser(int rollno) {
	Transaction transaction = null;
	try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	transaction = session.beginTransaction();
	User user = session.get(User.class, rollno);
	if (user != null) {
	session.remove(user);
	System.out.println("User deleted: " + rollno);
	}
	transaction.commit();
	} catch (Exception e) {
	if (transaction != null) {
	transaction.rollback();
	}
	e.printStackTrace();
	}
	}
}

