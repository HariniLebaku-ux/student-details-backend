
package com.pip.studentdetails.backend.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This entity represents the "department" table in the database.
 * students collection is mapped by the field in Student entity that refers to Department.
 */
@Entity
@Table(name = "department", schema="student_core")
public class Department {

    /**
     * Primary Key for Department table. Maps to column "department_id".
     */
    @Id
    @Column(name = "department_id", nullable = false)
    private String departmentId;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    /**
     * One-to-Many relationship, One department can have multiple students.
     * */
    @OneToMany(mappedBy = "departmentId", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Student> students = new LinkedHashSet<>();

    /**
     * Default constructor (required by JPA).
     */
    public Department() {}

    /**
     * Convenience constructor to create Department object quickly.
     * @param departmentId   unique department id (primary key)
     * @param departmentName department name
     */
    public Department(String departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }
}
