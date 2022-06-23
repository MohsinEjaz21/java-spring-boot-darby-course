package com.luv2code.hibernate.demo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class InstructorDemo {

  private static final Logger logger = LogManager.getLogger(InstructorDemo.class);  

  public static void main(String[] args) {


    // create session factory
    SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Instructor.class)
        .addAnnotatedClass(InstructorDetail.class)
        .buildSessionFactory();

    // create session
    Session session = factory.getCurrentSession();

    try {

      session.beginTransaction();
      
      
      findInstructorDetailById(session);
      
      // commit transaction
      session.getTransaction().commit();

      logger.info("Done!");
    } 
    catch(Exception e) {
      e.printStackTrace();
    }
    finally {
      session.close();
      factory.close();
    }
  }
  
  public static void findInstructorDetailById(Session session ) {
    int instructorDetailId =2;
    InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorDetailId);
    if(instructorDetail != null) {
      logger.info(instructorDetail);
      logger.info(instructorDetail.getInstructor());
    }    
  }
  
  private static void deleteInstructor(Session session) {
    int instructorPK = 1;
    Instructor instructor = session.get(Instructor.class, instructorPK);
    // delete the instructor
    // note: it will also delete the instructor Detail as it is CascadeType.ALL in instructor
    if(instructor !=null) {
      session.delete(instructor);
    }
  }
  
  private static void updateInstructor(Session session) {
    Instructor foundInstructor = session.get(Instructor.class, 2);
    
    if(foundInstructor != null) {
      foundInstructor.setFirstName("Mohsin");
      foundInstructor.setLastName("Ejaz");
      session.update(foundInstructor);
    }
  }

}
