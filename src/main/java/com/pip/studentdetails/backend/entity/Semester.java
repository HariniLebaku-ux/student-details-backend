 package com.pip.studentdetails.backend.entity;

 import javax.persistence.*;
 import java.util.LinkedHashSet;
 import java.util.Set;

 /**
  * JPA entity that maps to the "student_core.semester" table.
  * The relationship is mapped by the "semester" field in the Subject entity.
  */
 @Entity
 @Table(name = "semester", schema="student_core")
 public class Semester {

     /**
      * Primary key for the semester table.
      * Mapped to column "semester_id" (BIGINT in DB).
      */
     @Id
     @Column(name = "semester_id", nullable = false)
     private Long semesterId; // BIGINT

     /**
      * Name of the semester (e.g., "Semester 1", "Spring 2026").
      */
     @Column(name = "semester_name", nullable = false)
     private String semesterName;

     /**
      * Subjects offered in this semester.
      * LinkedHashSet is used to preserve insertion order and avoid duplicates.
      */
     @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, orphanRemoval = false)
     private Set<Subject> subjects = new LinkedHashSet<>();

     /**
      * Default constructor required by JPA.
      */
     public Semester() {}

     /** @return the semester ID */
     public Long getSemesterId() {
         return semesterId;
     }

     /** @param semesterId sets the semester ID */
     public void setSemesterId(Long semesterId) {
         this.semesterId = semesterId;
     }

     /** @return the semester name */
     public String getSemesterName() {
         return semesterName;
     }

     /** @param semesterName sets the semester name */
     public void setSemesterName(String semesterName) {
         this.semesterName = semesterName;
     }

     /** @return the set of subjects for this semester */
     public Set<Subject> getSubjects() {
         return subjects;
     }

     /** @param subjects sets the subject collection for this semester */
     public void setSubjects(Set<Subject> subjects) {
         this.subjects = subjects;
     }

     /**
      * Convenience constructor to create a Semester object with all fields.
      *
      * @param semesterId   Unique identifier for the semester
      * @param semesterName Display name of the semester
      * @param subjects     Set of subjects mapped to this semester
      */
     public Semester(Long semesterId, String semesterName, Set<Subject> subjects) {
         this.semesterId = semesterId;
         this.semesterName = semesterName;
         this.subjects = subjects;
     }
 }
