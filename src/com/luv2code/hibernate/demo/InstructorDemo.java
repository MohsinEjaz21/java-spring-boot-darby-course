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
      
      
//      findInstructorDetailById(session);
      addInstructorDetail(session);
      // commit transaction
      session.getTransaction().commit();

      logger.info("Done!");
    } 
    catch(Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
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
  
  private static void addInstructorDetail(Session session) {
    InstructorDetail instructorDetail = new InstructorDetail();
    instructorDetail.setInstructor(new Instructor("Kamran", "Ali","kamranAli@gmail.com"));
    instructorDetail.setHobby("Watering plants");
    instructorDetail.setYoutubeChannel("KamranAli.youtube.com");
    session.save(instructorDetail);
    instructorDetail.getInstructor().setId(instructorDetail.getId());
    session.update(instructorDetail);
  }

  // with cascade means the associated entity will also be deleted
  public static void deleteInstructorDetailWithCascade(Session session) {
    // as datatype is cascade so delete of instructorDetail alse delete instructor
    int instructorDetailPk = 7;
    InstructorDetail instructorDetail = session.find(InstructorDetail.class, instructorDetailPk);
    if(instructorDetail!=null) {
      session.delete(instructorDetail);      
    }
  }
  // without cascade.Type = delete means that we want the associated entity to preserve in db
  public static void deleteInstructorDetailWithoutCascade(Session session) {
    // delete of instructorDetail will not effect instructor entity
    int instructorDetailPk = 7;
    InstructorDetail instructorDetail = session.find(InstructorDetail.class, instructorDetailPk);
    // * break the relationship self reference instructorDetail
    // * note that we dont want to delete instructor on delete of instructorDetail
    //   hence we dont include cascadeType = Delete in @OneToOne
    // * Note if cascadeType.All as it would break the relationship automatically
    //   and would delete the instructor class as well which we dont want
    // * MUST check CascadeType.All is not used otherwise code purpose
    // not to delete associated entity fails
    if(instructorDetail !=null) {
      instructorDetail.getInstructor().setInstructorDetail(null);      
      session.delete(instructorDetail);
    }
    
    
  }
  
}
