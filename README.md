FSAD Workbooks Repository

Hibernate & Spring Boot Laboratory Experiments (Eclipse + Maven + MySQL)

This repository contains a complete collection of Full Stack Application Development (FSAD) workbook laboratory experiments implemented using Hibernate ORM, Hibernate Query Techniques, and Spring Boot with Spring Data JPA.

All experiments were developed in Eclipse IDE and integrated with a MySQL database for persistence and query execution.

📌 Technology Stack

The following tools and frameworks were used throughout the experiments:

Java

Eclipse IDE (2023+)

Maven

MySQL Database

Hibernate ORM

Hibernate Query Language (HQL)

Hibernate Criteria Query Language (HCQL)

Spring Boot Framework

Spring Data JPA

✅ Database Configuration

All laboratory experiments are executed using a common database schema.

Database Details

Database Name: show_prk

Username: root

Password: Abcdef@123

Table Structure: users
CREATE TABLE users (
  rollno INT PRIMARY KEY,
  name VARCHAR(50)
);

📂 List of Completed Experiments

✅ Experiment 1: Hibernate Basic Insert Operation

(Session 2 – Hibernate Setup and Insert User Records)

Objective

To build a Maven-based Hibernate project and perform the insertion of user records into a MySQL database.

Concepts Covered

Hibernate configuration using hibernate.cfg.xml

Entity mapping with User.java

SessionFactory creation and management

Insert operation using DAO layer

Sample Output
User inserted successfully into the database.

✅ Experiment 2: Fetch Records Using Hibernate Query Language (HQL)
Objective

To retrieve user records satisfying the following conditions:

rollno >= 10

name starts with the letter "P"

Query Used (HQL)
FROM User u
WHERE u.rollno >= :minRoll
AND u.name LIKE :pattern

Concepts Covered

HQL-based select queries

Named parameter binding

Result list processing

✅ Experiment 3: Fetch Records Using Hibernate Criteria Query Language (HCQL)

Objective

To perform the same filtering operation using Hibernate Criteria API instead of HQL.

Criteria Conditions
criteriaBuilder.ge(root.get("rollno"), 10);
criteriaBuilder.like(root.get("name"), "P%");

Concepts Covered

CriteriaBuilder and Predicate usage

Type-safe query execution

Dynamic query generation

✅ Experiment 4: Spring Boot + MySQL + JPA Integration
(Session 6 – Spring Boot Skill and Theory)

Objective

To develop a Spring Boot application that performs database operations using Spring Data JPA.

Functional Requirements

Insert user records

Fetch users where:

rollno >= 10

name starts with "P."

Spring Data Repository Method
List<User> findByRollnoGreaterThanEqualAndNameStartingWith(
    int rollno, String prefix
);

Concepts Covered

Spring Boot starter project setup

Database configuration through application.properties

Repository layer abstraction

Automatic query generation in Spring Data JPA

✅ Standard Project Structure

Each experiment follows a structured Maven architecture:

src/main/java
 ├── entity
 ├── dao / repository
 ├── util (HibernateUtil)
 └── main application class

src/main/resources
 ├── hibernate.cfg.xml   (Hibernate projects)
 └── application.properties (Spring Boot project)

▶️ Execution Instructions

Step 1: Clone the Repository
git clone https://github.com/your-username/your-repo-name.git

Step 2: Open in Eclipse IDE

File → Import → Existing Maven Project

Select the project directory

Step 3: Configure MySQL Database

Ensure MySQL service is running and the database show_prk is created.

Step 4: Run the Applications

Hibernate Projects: Run MainApp.java

Spring Boot Project: Run SpringBootApplication.java

✅ Learning Outcomes

By completing these experiments, the following competencies were gained:

Hibernate ORM integration in Maven-based applications

Entity-to-table mapping and database persistence

Data retrieval using HQL and Criteria API

Development of modern applications using Spring Boot + JPA

Repository-based query execution and abstraction

👨‍💻 Author Information

Name: Sama Thanvik Reddy
Department: Computer Science and Engineering
University: KLH University

📌 Repository Status

✅ All workbook experiments completed

✅ Uploaded and maintained using GitHub

✅ Verified outputs executed in Eclipse IDE
