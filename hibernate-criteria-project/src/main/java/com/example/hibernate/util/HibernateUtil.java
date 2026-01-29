package com.example.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.example.hibernate.model.User;

public class HibernateUtil {
	
private static SessionFactory sessionFactory;

static{
try{
//Create registry
StandardServiceRegistry registry=new StandardServiceRegistryBuilder()
.configure("hibernate.cfg.xml")
.build();
//CreateMetadataSources
MetadataSources sources=new MetadataSources(registry);
sources.addAnnotatedClass(User.class);
//CreateMetadata
Metadata metadata = sources.getMetadataBuilder().build();
//CreateSessionFactory
sessionFactory=metadata.getSessionFactoryBuilder().build();
System.out.println("✓Hibernate SessionFactory created successfully");
}catch(Exception e){
System.err.println("✗Failed to create SessionFactory:"+e.getMessage());
e.printStackTrace();
throw new ExceptionInInitializerError(e);
}
}
public static SessionFactory getSessionFactory(){
return sessionFactory;
}
public static void shutdown(){
if(sessionFactory!=null){
sessionFactory.close();
System.out.println("✓HibernateSessionFactoryclosed");
}
}
}

