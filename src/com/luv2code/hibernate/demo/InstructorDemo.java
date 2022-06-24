package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;

public class InstructorDemo {

  private static final Logger logger = LogManager.getLogger(InstructorDemo.class);

  public static void main(String[] args) {

    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
        .addAnnotatedClass(Course.class).buildSessionFactory();

    Session session = factory.getCurrentSession();

    try {

      session.beginTransaction();

      // Code Here ===== START

      findCoursesWithInstructor(session);

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

  static void findInstructorWithCourses(Session session) {
    int instructorPk = 30;
    Instructor instructor = session.find(Instructor.class, instructorPk);

    if (instructor != null) {
      logger.info(instructor);
      logger.info(instructor.getCourses());
    }
  }
  
  static void findCoursesWithInstructor(Session session) {
    int coursePk = 30;
    Course course = session.find(Course.class, coursePk);
    if(course !=null) {
      logger.info(course);
      logger.info(course.getInstructor());
    }
    
  }

  static void deleteInstructorWithCourses(Session session) {
    int instructorPk = 30;
    Instructor instructor = session.find(Instructor.class, instructorPk);

    if (instructor != null) {
      session.delete(instructor);
    }
  }

  static void deleteCourseWithoutDeleteInstructor(Session session) {
    int coursePk = 73;
    Course course = session.find(Course.class, coursePk);

    if (course != null) {
      session.delete(course);
    }
  }

  static void createCourseWithInstructor(Session session) {
    Instructor instructor = new Instructor("Mohsin", "Ejaz", "mejaz.bese21@gmail.com");
    Course course = new Course("Chemistry ABC", instructor);
    // session.save(course) doesnot work for cascadeType=Persist;
    session.persist(course);
  }

  static void createInstructorWithCourses(Session session) {
    List<Course> courses = new ArrayList<>();
    Instructor instructor = new Instructor("Hassan", "jani", "kashi.jani@gmail.com");
    courses.add(new Course("Course-3", instructor));
    courses.add(new Course("Course-4", instructor));
    instructor.setCourses(courses);
    session.persist(instructor);
  }

  // PROCESS FLOWS
  static void rollbackAfterSave(Session session) {
    Instructor instructor = new Instructor("Kashi", "jani", "kashi.jani@gmail.com");
    session.save(instructor);
    // rollback transaction
    // session.getTransaction().rollback();
  }

  static void updateInstructor(Session session) {
    int instructorPk = 161;
    Instructor instructor = session.find(Instructor.class, instructorPk);
    if (instructor != null) {
      instructor.setLastName("putr");
      session.merge(instructor);
    }
  }

}
