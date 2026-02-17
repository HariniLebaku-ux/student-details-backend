/*
package com.pip.studentdetails.backend.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;


*/
/**
 * Fast unit tests for the Student JPA entity (no persistence involved).
 *//*

class StudentTest {

    Student student = new Student();

    Department department = new Department();

    @Test
    void defaultConstructor_success() {

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
    @DisplayName("Convenience constructor should initialize required fields")
    void Constructorinitializesfields_Success() {

        Student s = new Student("ST_002", department, "B");

        assertThat(s.getStudentId()).isEqualTo("ST_002");
        assertThat(s.getDepartmentId()).isSameAs(department);
        assertThat(s.getSectionId()).isEqualTo("B");

        // studentName isn’t in the convenience ctor — should still be null
        assertThat(s.getStudentName()).isNull();
    }
}*/
