package com.luv2code.hibernate.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="instructor")
public class Instructor {

  public Instructor() {}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;
	
	
	// * look instructor in courses and by using join column pull out the information
	//   about courses associated to the instructor
	// * Cascade doesnot include Remove , b/c the requirement is not to remove entity
	//   courses if instructor is removed
	// * little trick to rememeber where to use one -> Many is see after '->'
	//   which is Many (so courses are many see below)
  // * How to read it ?
  //   Many courses are taught by one instructor
	@OneToMany(mappedBy="instructor", cascade={
	    CascadeType.PERSIST,
	    CascadeType.MERGE, 
      CascadeType.DETACH, 
      CascadeType.REFRESH,
      CascadeType.REMOVE
    })
	private List<Course> courses;
	

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  @Override
  public String toString() {
    return "Instructor [id=" + id + ", " + (firstName != null ? "firstName=" + firstName + ", " : "")
        + (lastName != null ? "lastName=" + lastName + ", " : "") + (email != null ? "email=" + email : "") + "]";
  }


  
}






