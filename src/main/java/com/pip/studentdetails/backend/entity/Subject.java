 package com.pip.studentdetails.backend.entity;

 import javax.persistence.*;

 /**
  * JPA entity that maps to the "student_core.subject" table.
  * Represents an academic subject (e.g., "Mathematics", "Physics") that belongs to a specific semester.
  * Many Subjects belong to one Semester (Many-to-One).
  */
 @Entity
 @Table(name = "subject")
 public class Subject {

     /**
      * Primary key for the subject table.
      * Mapped to column "subject_id".
      */
     @Id
     @Column(name = "subject_id", nullable = false)
     private String subjectId;

     /**
      * Name of the subject.
      * Mapped to column "subject_name".
      */
     @Column(name = "subject_name", nullable = false)
     private String subjectName;

     /**
      * Semester to which this subject belongs.
      * This references the primary key column in Semester (semester.semester_id).
      */
     @ManyToOne(optional = false, fetch = FetchType.LAZY)
     @JoinColumn(name = "semester_id", nullable = false)
     private Semester semester; // maps to SEMESTER.SEMESTER_ID (Long)

     /**
      * Default constructor required by JPA.
      */
     public Subject() {}

     /** @return the subject ID */
     public String getSubjectId() {
         return subjectId;
     }

     /** @param subjectId sets the subject ID */
     public void setSubjectId(String subjectId) {
         this.subjectId = subjectId;
     }

     /** @return the subject name */
     public String getSubjectName() {
         return subjectName;
     }

     /** @param subjectName sets the subject name */
     public void setSubjectName(String subjectName) {
         this.subjectName = subjectName;
     }

     /**
      * @return the semester associated with this subject
      * - Because FetchType is LAZY, accessing this field may trigger a DB query
      *   if it is not already loaded within an active persistence context.
      */
     public Semester getSemester() {
         return semester;
     }

     /** @param semester sets the semester association */
     public void setSemester(Semester semester) {
         this.semester = semester;
     }

     /**
      * Convenience constructor to create a Subject object with all fields.
      */
     public Subject(String subjectId, String subjectName, Semester semester) {
         this.subjectId = subjectId;
         this.subjectName = subjectName;
         this.semester = semester;
     }
 }
