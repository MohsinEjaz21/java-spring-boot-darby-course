package com.luv2code.hibernate.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Student;

public class InstructorDemo {

  private static final Logger logger = LogManager.getLogger(InstructorDemo.class);
  static Session session = null;

  public static void main(String[] args) {

    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
        .addAnnotatedClass(Student.class)
        .addAnnotatedClass(Course.class).buildSessionFactory();

    session = factory.getCurrentSession();

    try {

      session.beginTransaction();

      // Code Here ===== START
      deleteCourse();
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

  static void addCourse() {
    Course course = new Course("Dramatic Photography");
    course.addStudent(new Student("Mohsin", "Ejaz", "mejaz.bese21@mailinator.com"));
    course.addStudent(new Student("kamran", "Safdar", "kamaran.safdar@mailinator.com"));
    session.persist(course);
  }

  static void getCourse() {
    int pkCourse = 101;
    Course course = session.find(Course.class, pkCourse);
    if(course != null) {
      logger.info(course);
      logger.info(course.getStudents());
    }
  }
  
  static void deleteCourse() {
    int pkCourse = 1;
    Course course = session.find(Course.class, pkCourse);
    if(course != null) {
      session.delete(course);
    }
  }
  
  static void addStudent() {
    Student student = new Student("Adnan", "Ashraf", "adnan.ashraf@mailinator.com");
    student.addCourse(new Course("English CS-101"));
    student.addCourse(new Course("Chemistry CS-101"));
    session.persist(student);
  }

  static void getStudent() {
    int pkStudentId = 101;
    Student student = session.find(Student.class, pkStudentId);
    if(student !=null) {
      logger.info(student);
      logger.info(student.getCourses());
    }
  }
  

  
}
