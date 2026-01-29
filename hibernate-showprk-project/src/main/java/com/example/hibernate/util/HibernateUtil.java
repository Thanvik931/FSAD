package com.example.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.example.hibernate.model.User;
public class HibernateUtil {
private static SessionFactory sessionFactory;
static {
try {
// Create Configuration
Configuration configuration = new Configuration();
// Configure hibernate.cfg.xml
configuration.configure("hibernate.cfg.xml");
// Add annotated class
configuration.addAnnotatedClass(User.class);
// Build SessionFactory
sessionFactory = configuration.buildSessionFactory();
} catch (Exception e) {
System.err.println("Initial SessionFactory creation failed: " + e);
throw new ExceptionInInitializerError(e);
}
}
public static SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	public static void shutdown(){
	if(sessionFactory!=null){
	sessionFactory.close();
	}
	}
}