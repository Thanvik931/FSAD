# FSAD
All Workbook tasks 
Hibernate & Spring Boot Lab Experiments (Eclipse + Maven + MySQL)

This repository contains a complete set of laboratory experiments implemented using Hibernate ORM, Hibernate Query Techniques, and Spring Boot with JPA.

All projects were developed in Eclipse IDE and connected with a MySQL database.

📌 Tech Stack Used

Java

Eclipse IDE (2023+)

Maven

MySQL Database

Hibernate ORM

Hibernate Query Language (HQL)

Hibernate Criteria Query Language (HCQL)

Spring Boot

Spring Data JPA

✅ Database Configuration

All experiments use the same database and table setup.

Database Details

Database Name: show_prk

Username: root

Password: Abcdef@123

Table: users
CREATE TABLE users (
  rollno INT PRIMARY KEY,
  name VARCHAR(50)
);

📂 List of Completed Experiments
✅ Experiment 1: Hibernate Basic Insert Operation

(Session 2 Lab – Hibernate Setup + Insert Users)

Objective

To create a Maven Hibernate project and insert user records into a MySQL database using Hibernate ORM.

Concepts Covered

Hibernate Configuration (hibernate.cfg.xml)

Entity Mapping (User.java)

SessionFactory Setup

Insert Operation using DAO

Output Example
User inserted successfully into database.

✅ Experiment 2: Fetch Users Using Hibernate Query Language (HQL)
Objective

To retrieve all users where:

rollno >= 10

name starts with "P"

Query Used (HQL)
FROM User u
WHERE u.rollno >= :minRoll
AND u.name LIKE :pattern

Concepts Covered

HQL Select Queries

Named Parameters

Result List Processing

✅ Experiment 3: Fetch Users Using Hibernate Criteria Query Language (HCQL)
Objective

To perform the same filtering operation using Criteria API instead of HQL.

Criteria Conditions
criteriaBuilder.ge(root.get("rollno"), 10);
criteriaBuilder.like(root.get("name"), "P%");

Concepts Covered

CriteriaBuilder

Predicate Conditions

Type-safe Query Execution

✅ Experiment 4: Spring Boot + MySQL + JPA Integration

(Session 6 – Spring Boot Skill + Theory)

Objective

To develop a Spring Boot application that performs database operations using Spring Data JPA.

Functional Requirements

Insert User Records

Fetch users where:

rollno >= 10

name starts with "P"

Spring Repository Query Method
List<User> findByRollnoGreaterThanEqualAndNameStartingWith(
    int rollno, String prefix
);

Concepts Covered

Spring Boot Starter Projects

application.properties configuration

Spring Data Repository Layer

Auto Query Generation

✅ Project Structure Followed

Each project contains the standard architecture:

src/main/java
 ├── entity
 ├── dao / repository
 ├── util (HibernateUtil)
 └── main application class

src/main/resources
 ├── hibernate.cfg.xml (Hibernate projects)
 └── application.properties (Spring Boot)

▶️ How to Run the Projects
Step 1: Clone Repository
git clone https://github.com/your-username/your-repo-name.git

Step 2: Open in Eclipse

File → Import → Existing Maven Project

Select the project folder

Step 3: Configure MySQL Database

Ensure MySQL is running and database show_prk exists.

Step 4: Run the Application

Hibernate Projects: Run MainApp.java

Spring Boot Project: Run SpringBootApplication.java

✅ Learning Outcomes

By completing these experiments, the following skills were achieved:

Hibernate ORM Setup in Maven Project

Entity Mapping and Table Integration

Performing Insert and Fetch Operations

Writing Queries using:

HQL

Criteria API

Building Modern Applications using Spring Boot + JPA

Repository-based Query Execution

👨‍💻 Author

Name: Sama Thanvik Reddy
Department: Computer Science and Engineering
University: KLH University

📌 Repository Status

✅ All experiments successfully completed
✅ Uploaded and managed using GitHub
✅ Verified output in Eclipse
