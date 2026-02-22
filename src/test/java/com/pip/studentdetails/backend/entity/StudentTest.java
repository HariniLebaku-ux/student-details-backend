package com.pip.studentdetails.backend.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/** Fast unit tests for the Student JPA entity (no persistence involved).*/

class StudentTest {

    Student student = new Student();

    Department department = new Department();

    @Test
    void studentDefaultConstructor_success() {

        student.setStudentId("ST_001");
        student.setStudentName("John");
        student.setDepartmentId(department);     // ManyToOne field
        student.setSectionId("A");

        assertThat(student.getStudentId()).isEqualTo("ST_001");
        assertThat(student.getStudentName()).isEqualTo("John");
        assertThat(student.getDepartmentId()).isSameAs(department);
        assertThat(student.getSectionId()).isEqualTo("A");
    }

    @Test
    void StudentNullDetails_Failure() {

        Student s = new Student("ST_002",null, department, "B");

        assertThat(s.getStudentId()).isEqualTo("ST_002");
        //assertThat(s.getStudentName()).isEqualTo("John");
        assertThat(s.getDepartmentId()).isSameAs(department);
        assertThat(s.getSectionId()).isEqualTo("B");

        // studentName isn’t in the convenience ctor — should still be null
        assertThat(s.getStudentName()).isNull();
    }
}
