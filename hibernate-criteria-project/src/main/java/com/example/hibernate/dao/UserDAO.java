package com.example.hibernate.dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.criteria.JpaRoot;
import com.example.hibernate.model.User;
import com.example.hibernate.util.HibernateUtil;
public class UserDAO{
//====================CREATEOPERATIONS====================
/**
*Insertasingleuser
*/
public void saveUser(User user){
Transaction transaction = null;
Session session = null;
try{
session = HibernateUtil.getSessionFactory().openSession();
transaction = session.beginTransaction();
session.persist(user);
transaction.commit();
System.out.println("✓Usersaved:"+user);
}catch(Exception e){
if(transaction!=null){
transaction.rollback();
}
System.err.println("✗Errorsavinguser:"+e.getMessage());
e.printStackTrace();
}finally{
if(session!=null&&session.isOpen()){
session.close();
}
}
}
/**
*Insertmultipleusers(batchinsert)
*/
public void saveUsers(List<User> users){
Transaction transaction = null;
Session session = null;
try {
session = HibernateUtil.getSessionFactory().openSession();
transaction = session.beginTransaction();
for (int i = 0; i < users.size(); i++){
session.persist(users.get(i));
// Batch processing
if (i % 20 == 0) {
session.flush();
session.clear();
}
}
transaction.commit();
System.out.println("✓ " + users.size() + " users saved successfully!");
} catch (Exception e) {
if (transaction != null) {
transaction.rollback();
}
System.err.println("✗ Error saving users: " + e.getMessage());
e.printStackTrace();
} finally {
if (session != null && session.isOpen()) {
session.close();
		}
	}
}

// ==================== READ OPERATIONS (HCQL) ====================
/**
* Get user by ID using Criteria API
*/
public User getUserById(int rollno) {
Session session = null;
try {
session = HibernateUtil.getSessionFactory().openSession();
// Create CriteriaBuilder
HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//Create CriteriaQuery
JpaCriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//Define Root
JpaRoot<User> root = criteriaQuery.from(User.class);
//Add WHEREclause
criteriaQuery.select(root)
.where(criteriaBuilder.equal(root.get("rollno"), rollno));
//Execute query
return session.createQuery(criteriaQuery).uniqueResult();
} catch (Exception e) {
System.err.println("✗ Error getting user by ID: " + e.getMessage());
return null;
} finally {
if (session != null && session.isOpen()) {
session.close();
}
}
}
/**
* Get ALL users using Criteria API
*/
public List<User> getAllUsers() {
Session session = null;
try {
session = HibernateUtil.getSessionFactory().openSession();
//HCQL: SELECT * FROM users
HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
JpaCriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
JpaRoot<User> root = criteriaQuery.from(User.class);
criteriaQuery.select(root);
return session.createQuery(criteriaQuery).getResultList();
} catch (Exception e) {
System.err.println("✗ Error getting all users: " + e.getMessage());
return null;
} finally {
if (session != null && session.isOpen()) {
session.close();
}
}
}
/**
* ======================================================
* YOURASSIGNMENT: HCQLQuery
* Fetch users with rollno >= 10 AND name starting with "P"
* ======================================================
*/
public List<User> getUsersWithCriteria() {
Session session = null;
try {
session = HibernateUtil.getSessionFactory().openSession();
// Step 1: Get CriteriaBuilder
HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
// Step 2: Create CriteriaQuery for User
JpaCriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
// Step 3: Define Root (FROM User)
JpaRoot<User> root = criteriaQuery.from(User.class);
// Step 4: Build Predicates (WHERE conditions)
// Condition 1: rollno >= 10
JpaPredicate rollnoCondition = criteriaBuilder.ge(root.get("rollno"), 10);
// Condition 2: name LIKE 'P%'
JpaPredicate nameCondition = criteriaBuilder.like(root.get("name"), "P%");
// Step 5: Combine predicates with AND
JpaPredicate finalCondition = criteriaBuilder.and(rollnoCondition, nameCondition);
// Step 6: Build complete query
criteriaQuery.select(root)
.where(finalCondition)
.orderBy(criteriaBuilder.asc(root.get("rollno"))); // Optional: Order by rollno
// Step 7: Execute query
List<User> result = session.createQuery(criteriaQuery).getResultList();
System.out.println("✓ HCQL Query executed successfully");
System.out.println(" Criteria: rollno >= 10 AND name LIKE 'P%'");
return result;
} catch (Exception e) {
System.err.println("✗ Error in HCQL query: " + e.getMessage());
e.printStackTrace();
return null;
} finally {
if (session != null && session.isOpen()) {
session.close();
}
}
}
/**
* Alternative: HCQL with dynamic parameters
*/
public List<User> getUsersWithCriteriaDynamic(int minRollno, String namePattern) {
Session session = null;
try {
session = HibernateUtil.getSessionFactory().openSession();
HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
JpaCriteriaQuery<User> cq = cb.createQuery(User.class);
JpaRoot<User> root = cq.from(User.class);
// Build conditions
JpaPredicate rollnoCondition = cb.ge(root.get("rollno"), minRollno);
JpaPredicate nameCondition = cb.like(root.get("name"), namePattern);
// Combine
cq.select(root).where(cb.and(rollnoCondition, nameCondition));
return session.createQuery(cq).getResultList();
} catch (Exception e) {
System.err.println("✗ Error in dynamic HCQL: " + e.getMessage());
return null;
} finally {
if (session != null && session.isOpen()) {
session.close();
}
}
}
// ==================== UPDATE OPERATIONS ====================
/**
* Update user using Criteria API (alternative approach)
*/
public void updateUser(User user) {
Transaction transaction = null;
Session session = null;
try {
session = HibernateUtil.getSessionFactory().openSession();
transaction = session.beginTransaction();
session.merge(user);
transaction.commit();
System.out.println("✓ User updated: " + user);
} catch (Exception e) {
if (transaction != null) {
transaction.rollback();
}
System.err.println("✗ Error updating user: " + e.getMessage());
} finally {
if (session != null && session.isOpen()) {
session.close();
}
}
}
// ==================== DELETE OPERATIONS ====================
/**
* Delete user by ID using Criteria API
*/
public void deleteUser(int rollno) {
Transaction transaction = null;
Session session = null;
try {
session = HibernateUtil.getSessionFactory().openSession();
transaction = session.beginTransaction();
User user = session.get(User.class, rollno);
if (user != null) {
session.remove(user);
System.out.println("✓ User deleted: " + rollno);
}
transaction.commit();
} catch (Exception e) {
if (transaction != null) {
transaction.rollback();
}
System.err.println("✗ Error deleting user: " + e.getMessage());
} finally {
if (session != null && session.isOpen()) {
	session.close();
	}
	}
	}
	// ==================== UTILITY METHODS ====================
	/**
	* Verify data in database
	*/
	public void verifyDataInDatabase() {
	Session session = null;
	try {
	session = HibernateUtil.getSessionFactory().openSession();
	// Using Criteria API for count
	HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
	JpaCriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
	JpaRoot<User> root = countQuery.from(User.class);
	countQuery.select(cb.count(root));
	Long count = session.createQuery(countQuery).uniqueResult();
	System.out.println("✓ Total users in database: " + count);
	// Get all users
	List<User> users = getAllUsers();
	if (users == null || users.isEmpty()) {
	System.out.println("✗ Database is empty!");
	} else {
	System.out.println("✓ Retrieved " + users.size() + " users:");
	for (User user : users) {
	System.out.println("- " + user);
	}
	}
	} catch (Exception e) {
	System.err.println("✗ Error verifying database: " + e.getMessage());
	e.printStackTrace();
	} finally {
	if (session != null && session.isOpen()) {
	session.close();
	}
	}
	}
	/**
	* Count users with Criteria API
}
	*/
	public Long countUsers(){
	Session session=null;
	try{
	session=HibernateUtil.getSessionFactory().openSession();
	HibernateCriteriaBuilder cb=session.getCriteriaBuilder();
	JpaCriteriaQuery<Long>cq=cb.createQuery(Long.class);
	JpaRoot<User>root=cq.from(User.class);
	cq.select(cb.count(root));
	return session.createQuery(cq).uniqueResult();
	}catch(Exception e){
	System.err.println("✗Errorcountingusers:"+e.getMessage());
	return 0L;
	}finally{
	if(session!=null&&session.isOpen()){
	session.close();
	}
	}
	}
	}