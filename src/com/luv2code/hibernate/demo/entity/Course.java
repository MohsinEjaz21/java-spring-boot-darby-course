package com.luv2code.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="course")
public class Course {
  
  public Course() {}
  
  

  public Course(String title) {
    super();
    this.title = title;
  }



  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id")
  private int id;
  
  @Column(name="title", unique=true)
  private String title;
  
  
  @ManyToMany(
  fetch=FetchType.LAZY,
  cascade= {CascadeType.PERSIST, CascadeType.MERGE,
      CascadeType.DETACH, CascadeType.REFRESH}
  )
  @JoinTable(
    name="course_student",
    joinColumns=@JoinColumn(name="course_id"),
    inverseJoinColumns=@JoinColumn(name="student_id")
   )
  private List<Student> students;


  public int getId() {
    return id;
  }


  public String getTitle() {
    return title;
  }


  public List<Student> getStudents() {
    return students;
  }


  public void setId(int id) {
    this.id = id;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public void setStudents(List<Student> students) {
    this.students = students;
  }


  @Override
  public String toString() {
    return "Course [id=" + id + ", " + (title != null ? "title=" + title : "") + "]";
  }


  public void addStudent(Student student) {
    if(students == null) {
      students = new ArrayList<>();
    }
    students.add(student);
  }
  
  
}






