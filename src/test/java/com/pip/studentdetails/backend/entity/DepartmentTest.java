/*
package com.pip.studentdetails.backend.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashSet;
import static org.assertj.core.api.Assertions.assertThat;

class DepartmentTest {

    @Test
    void defaultCtor_success() {
        Department d = new Department();
        d.setDepartmentId("DEPT_CS");
        d.setDepartmentName("Computer Science");
        d.setStudents(new LinkedHashSet<>());

        assertThat(d.getDepartmentId()).isEqualTo("DEPT_CS");
        assertThat(d.getDepartmentName()).isEqualTo("Computer Science");
        assertThat(d.getStudents()).isNotNull();
    }

    @Test
    void convenienceCtor() {
        Department d = new Department("DEPT_IT", "Information Technology");

        assertThat(d.getDepartmentId()).isEqualTo("DEPT_IT");
        assertThat(d.getDepartmentName()).isEqualTo("Information Technology");
        assertThat(d.getStudents()).isNotNull(); // initialized in field declaration
    }
}*/
