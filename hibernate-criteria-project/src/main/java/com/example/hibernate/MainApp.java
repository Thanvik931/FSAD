package com.example.hibernate;
import java.util.Arrays;
import java.util.List;
import com.example.hibernate.dao.UserDAO;
import com.example.hibernate.model.User;

public class MainApp {
	public static void main(String[]args){
System.out.println("╔════════════════════════════════════════════════════════╗");
System.out.println("║ HIBERNATECRITERIAQUERY(HCQL)PROJECT ║");
System.out.println("║ Database:show_prk|Table:users ║");
System.out.println("║ UsingHibernateCriteriaAPI(HCQL) ║");
System.out.println("╚════════════════════════════════════════════════════════╝");
System.out.println();
UserDAO userDAO=new UserDAO();
try{
//==========STEP1:INSERTSAMPLEDATA==========
System.out.println("┌─────────────────────────────────────────────────────┐");
System.out.println("│STEP1:INSERTINGSAMPLEDATA │");
System.out.println("└─────────────────────────────────────────────────────┘");
List<User>sampleUsers=Arrays.asList(
new User(1,"Ramesh"),
new User(2,"Babu"),
new User(10,"Prasanna"),
new User(11,"Prasad"),
new User(12,"Paul"),
new User(20,"Joshi"),
new User(21,"Pavan"),
new User(30,"Pravin"),
new User(40,"Mohan"),
new User(50,"Prince"),
new User(60,"Penny")
);
userDAO.saveUsers(sampleUsers);
//==========STEP2:VERIFYDATA==========
System.out.println("\n┌─────────────────────────────────────────────────────┐");
System.out.println("│STEP2:VERIFYINGDATA │");
System.out.println("└─────────────────────────────────────────────────────┘");
userDAO.verifyDataInDatabase();
// ========== STEP 3: YOUR HCQL QUERY ==========
System.out.println("\n┌─────────────────────────────────────────────────────┐");
System.out.println("│ STEP 3: EXECUTING HCQL QUERY│");
System.out.println("│								│");
System.out.println("│ HCQL Criteria:│");
System.out.println("│ • rollno >= 10 │");
System.out.println("│ • name LIKE 'P%' │");
System.out.println("│					│");
System.out.println("│ Equivalent SQL:		│");
System.out.println("│ SELECT * FROM users│");
System.out.println("│ WHERE rollno >= 10 AND name LIKE 'P%'│");
System.out.println("│ ORDER BY rollno │");
System.out.println("└─────────────────────────────────────────────────────┘");
List<User> hcqlResults = userDAO.getUsersWithCriteria();
if (hcqlResults == null || hcqlResults.isEmpty()) {
System.out.println("\n✗ No users found matching the criteria.");
} else {
System.out.println("\n✓ HCQL Results found (" + hcqlResults.size() + " users):");
System.out.println("┌─────────┬──────────────────────┐");
System.out.println("│ Rollno │ Name│");
System.out.println("├─────────┼──────────────────────┤");
for (User user : hcqlResults) {
System.out.printf("│ %-7d │ %-20s │\n",
user.getRollno(), user.getName());
}
System.out.println("└─────────┴──────────────────────┘");
// Show expected users
System.out.println("\nExpected users in result:");
System.out.println("• Peter (rollno=10)");
System.out.println("• Parker (rollno=11)");
System.out.println("• Paul (rollno=12)");
System.out.println("• Pamela (rollno=21)");
System.out.println("• Patricia (rollno=30)");
System.out.println("• Prince (rollno=50)");
System.out.println("• Penny (rollno=60)");
}
// ========== STEP 4: DYNAMIC HCQL ==========
System.out.println("\n┌─────────────────────────────────────────────────────┐");
System.out.println("│ STEP 4: DYNAMIC HCQL EXAMPLE│");
System.out.println("└─────────────────────────────────────────────────────┘");
List<User> dynamicResults = userDAO.getUsersWithCriteriaDynamic(20, "P%");
if (dynamicResults != null && !dynamicResults.isEmpty()) {
System.out.println("Dynamic HCQL (rollno >= 20, name LIKE 'P%'):");
System.out.println("Found " + dynamicResults.size() + " users");
for (User user : dynamicResults) {
System.out.println("- " + user);
}
}
// ========== STEP 5: COMPARE WITH TRADITIONAL HQL ==========
System.out.println("\n┌─────────────────────────────────────────────────────┐");
System.out.println("│ STEP 5: HCQL vs HQL COMPARISON│");
System.out.println("└─────────────────────────────────────────────────────┘");
System.out.println("Traditional HQL would be:");
System.out.println(" String hql = \"FROM User u WHERE u.rollno >= :roll AND u.name LIKE :name\";");
System.out.println(" Query query = session.createQuery(hql, User.class);");
System.out.println(" query.setParameter(\"roll\", 10);");
System.out.println(" query.setParameter(\"name\", \"P%\");");
System.out.println();
System.out.println("HCQL Advantages:");
System.out.println(" • Type-safe (compile-time checking)");
System.out.println(" • No string concatenation for queries");
System.out.println(" • IDE auto-completion support");
System.out.println(" • Dynamic query building");
// ========== STEP 6: DATABASE VERIFICATION ==========
System.out.println("\n┌─────────────────────────────────────────────────────┐");
System.out.println("│ STEP 6: DATABASE VERIFICATION │");
System.out.println("└─────────────────────────────────────────────────────┘");
System.out.println("To verify in MySQL, run these commands:");
System.out.println(" mysql-u root-p");
System.out.println(" Enter password: Abcdef@123");
System.out.println(" USE show_prk;");
System.out.println(" SELECT * FROM users;");
System.out.println(" SELECT COUNT(*) FROM users;");
System.out.println(" SELECT * FROM users WHERE rollno >= 10 AND name LIKE 'P%';");
// ========== STEP 7: CLEANUP (Optional) ==========
System.out.println("\n┌─────────────────────────────────────────────────────┐");
System.out.println("│ STEP 7: CLEANUP (Optional)│");
System.out.println("└─────────────────────────────────────────────────────┘");
System.out.println("To clean the database, uncomment this in code:");
System.out.println(" // userDAO.deleteAllUsers();");
} catch (Exception e) {
System.err.println("\n✗ Fatal error: " + e.getMessage());
e.printStackTrace();
} finally {
// Cleanup
com.example.hibernate.util.HibernateUtil.shutdown();
System.out.println("\n" + "═".repeat(60));
System.out.println(" APPLICATION COMPLETED SUCCESSFULLY!");
System.out.println(" HCQL Query executed: rollno >= 10 AND name LIKE 'P%'");
System.out.println("═".repeat(60));
}
}
}