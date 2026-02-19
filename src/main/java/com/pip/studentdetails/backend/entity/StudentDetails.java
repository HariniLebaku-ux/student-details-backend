package com.pip.studentdetails.backend.entity;

import javax.persistence.*;

/**
 * This entity maps to the table: student_core.student_details
 * 1) Composite Primary Key is used: (student_id, subject_id)
 * 2) student_id and subject_id are present inside the embedded id,
 */
@Entity
@Table(name = "student_details")
public class StudentDetails {

    /**
     * The StudentDetailsId class should be annotated with @Embeddable and should contain these two fields.
     */
    @EmbeddedId
    private StudentDetailsId id;

    /**
     * student_id is fetch primary key from student table, it is composite key in student_details table
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="student_id", referencedColumnName="student_id", insertable=false, updatable=false)
    private Student student;

    /**
     * subject_id is fetch primary key from subject table, it is composite key in student_details table
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id", referencedColumnName="subject_id", insertable=false, updatable=false)
    private Subject subject;

    /**
     * Marks obtained for the subject. Default value is defined in DB as 0 (depends on DDL).
     */
    @Column(name = "marks")
    private Integer marks;

    /** Default constructor required by JPA */
    public StudentDetails() {}

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public StudentDetails(StudentDetailsId id, Student student, Subject subject, Integer marks) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.marks = marks;
    }
}
