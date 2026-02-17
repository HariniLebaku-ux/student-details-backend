package com.pip.studentdetails.backend.entity;

import javax.persistence.*;
/**
 * JPA entity that maps to the "student_core.student" table.
 * Represents a student and their associated department and section.
 */
@Entity
@Table(name = "student")
public class Student {

    /**
     * Primary key for the student table. Mapped to column "student_id".
     */
    @Id
    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    /**
     * department_id is fetch from the department table.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department departmentId;

    /**
     * Section identifier for the student (e.g., "A", "B", "C"). Mapped to column "section".
     * The Java field name is sectionId, but the DB column is "section".
     */
    @Column(name = "section", nullable = false)
    private String sectionId;

    /**
     * Default constructor required by JPA.
     */
    public Student() {}

    /** @return the student ID */
    public String getStudentId() {
        return studentId;
    }

    /** @param studentId sets the student ID */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {return studentName;}

    public void setStudentName(String studentName) {this.studentName = studentName;
    }

    /**@return the Department entity linked to this student*/
    public Department getDepartmentId() {
        return departmentId;
    }

    /** @param departmentId sets the department association */
    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    /** @return the section identifier */
    public String getSectionId() {
        return sectionId;
    }

    /** @param sectionId sets the section identifier */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * Convenience constructor to create a Student object.
     */
    public Student(String studentId, Department departmentId, String sectionId) {
        this.studentId = studentId;
        this.departmentId = departmentId;
        this.sectionId = sectionId;
    }
}
