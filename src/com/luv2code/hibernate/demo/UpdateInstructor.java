package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class UpdateInstructor {
  public static void main(String[] args) {
    SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Instructor.class)
        .addAnnotatedClass(InstructorDetail.class)
        .buildSessionFactory();
    
    // getCurrentSession from factory
    Session session = factory.getCurrentSession();
    
    try {
      // begin transaction
      session.beginTransaction();
      
      // find instructorById 
      
      Instructor foundInstructor = session.get(Instructor.class, 2);
      
      if(foundInstructor != null) {
        foundInstructor.setFirstName("Mohsin");
        foundInstructor.setLastName("Ejaz");
        session.update(foundInstructor);
      }
      
      session.getTransaction().commit();
    }
    catch(Exception e) {
      session.getTransaction().rollback();
      e.printStackTrace();
    }
    finally {
      session.close();
      factory.close();
    }
    
  }
}

