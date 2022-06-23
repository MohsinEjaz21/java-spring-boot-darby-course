package com.luv2code.hibernate.demo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FindInstructor {

  private static final Logger logger = LogManager.getLogger(FindInstructor.class);  

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
      
      
      findInstructorDetailById(session, 2);
      
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
  
  public static void findInstructorDetailById(Session session , int instructorDetailId ) {
    // your changes comes here
      logger.debug("Hello");
    
    InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorDetailId);
    
    if(instructorDetail != null) {
      logger.info(instructorDetail);
      logger.info(instructorDetail.getInstructor());
    }
    
  }

}
