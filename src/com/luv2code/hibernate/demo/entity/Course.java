package com.luv2code.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id")
  private int id;
  
  @Column(name="title")
  private String title;
  
  // * little trick to rememeber where to use Many -> One is see after '->'
  //   which is One (so instructor is One ... see below)
  // * How to read it ?
  //   One instructor can taught many courses
  @ManyToOne(cascade={
      CascadeType.DETACH, 
      CascadeType.MERGE, 
      CascadeType.PERSIST,
      CascadeType.REFRESH
    })
  @JoinColumn(name="instructor_id")
  private Instructor instructor;

  @Override
  public String toString() {
    return "Course [id=" + id + ", " + (title != null ? "title=" + title + ", " : "") + (instructor != null
        ? "instructor=" + instructor
        : "") + "]";
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Instructor getInstructor() {
    return instructor;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  public Course(int id, String title, Instructor instructor) {
    super();
    this.id = id;
    this.title = title;
    this.instructor = instructor;
  }

  
  
}






