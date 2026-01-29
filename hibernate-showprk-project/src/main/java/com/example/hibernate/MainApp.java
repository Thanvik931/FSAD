package com.example.hibernate;
import java.util.Arrays;
import java.util.List;
import com.example.hibernate.dao.UserDAO;
import com.example.hibernate.model.User;
public class MainApp {
public static void main(String[] args) {
System.out.println("=== Hibernate MySQL ShowPRK Application ===");
System.out.println("Connecting to database: show_prk");
System.out.println("Table: users\n");
UserDAO userDAO = new UserDAO();
try {
// Step 1: Create and insert sample users
System.out.println("\n1. Inserting sample users...");
List<User> sampleUsers = Arrays.asList(
new User(1, "Rajanna"),
new User(2, "Chandu"),
new User(10, "Prasad"),
new User(11, "Prasanna"),
new User(13, "Paul"),
new User(15, "Anitha"),
new User(21, "Pamela"),
new User(30, "Sushmita"),
new User(40, "Mahesh")
);
userDAO.saveUsers(sampleUsers);
// Step 2: Execute HQL query
System.out.println("\n2. Executing HQL Query:");
System.out.println(" HQL: FROM User u WHERE u.rollno >= :minRoll AND u.name LIKE :namePattern");
System.out.println(" Parameters: minRoll = 10, namePattern = 'P%'");
System.out.println("\n Results:");
List<User> filteredUsers = userDAO.getUsersWithRollnoAndName();
if (filteredUsers.isEmpty()) {
System.out.println(" No users found matching the criteria.");
} else {
for (User user : filteredUsers) {
System.out.println("- " + user);
}
System.out.println("\n Total users found: " + filteredUsers.size());
}
//Step 3: Display all users
System.out.println("\n3. All users in database:");
List<User> allUsers = userDAO.getAllUsers();
for (User user : allUsers) {
System.out.println(" " + user);
}
//Step 4: Cleanup (optional- comment out if you want to keep data)
/* System.out.println("\n4. Cleaning up database...");
for (User user : sampleUsers) {
userDAO.deleteUser(user.getRollno());
} */
} finally {
//Close Hibernate session factory
com.example.hibernate.util.HibernateUtil.shutdown();
System.out.println("\n=== Application Completed Successfully ===");
}
}
}