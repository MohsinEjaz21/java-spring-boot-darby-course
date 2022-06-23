package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Student;

public class InstructorDemo {

  private static final Logger logger = LogManager.getLogger(InstructorDemo.class);

  public static void main(String[] args) {

    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
        .addAnnotatedClass(Student.class)
        .addAnnotatedClass(Course.class).buildSessionFactory();

    Session session = factory.getCurrentSession();

    try {

      session.beginTransaction();

      // Code Here ===== START

      
      // Code Here ===== END

      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
    } finally {
      session.close();
      factory.close();
      logger.info("Done!");
    }
  }



}
