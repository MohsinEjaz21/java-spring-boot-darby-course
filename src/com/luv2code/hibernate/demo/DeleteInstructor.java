package com.luv2code.hibernate.demo;


import javax.persistence.CascadeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructor {

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
		  // start a transaction
		  session.beginTransaction();
		  
		  // get instructor by instructorPK
		  int instructorId = 1;
		  Instructor instructor = session.get(Instructor.class, instructorId);
		  
		  // delete the instructor
		  // note: it will also delete the instructor Detail as it is CascadeType.ALL in instructor
		  if(instructor !=null) {
		    session.delete(instructor);
		  }
		  // commit transaction
		  session.getTransaction().commit();
		  
		}
		catch(Exception e) {
		  session.getTransaction().rollback();
		  e.printStackTrace();
		} 
		finally {
		  session.close();
		  factory.close();
      System.out.println("Its Done");
		}
	}

}





