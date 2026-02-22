package com.pip.studentdetails.backend.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DepartmentPassPercentageRecordTest {

    @Test
    @DisplayName("Accessor methods should return constructor values")
    void accessors_ShouldReturnValues() {
        DepartmentPassPercentageRecord departmentPassPercentageRecord = new DepartmentPassPercentageRecord(
                "Computer Science and Engineering",
                "SEM_1",
                "SUB_001",
                "English",
                "A",
                85
        );

        assertThat(departmentPassPercentageRecord.departmentName()).isEqualTo("Computer Science and Engineering");
        assertThat(departmentPassPercentageRecord.semesterId()).isEqualTo("SEM_1");
        assertThat(departmentPassPercentageRecord.subjectId()).isEqualTo("SUB_001");
        assertThat(departmentPassPercentageRecord.subjectName()).isEqualTo("English");
        assertThat(departmentPassPercentageRecord.section()).isEqualTo("A");
        assertThat(departmentPassPercentageRecord.marks()).isEqualTo(85);
    }

    @Test
    @DisplayName("Record equality and hashCode should be structural")
    void equality_ShouldBeStructural() {
        DepartmentPassPercentageRecord departmentPassPercentageRecord = new DepartmentPassPercentageRecord(
                "CS", "SEM_1", "SUB_1", "OS", "A", 90
        );
        DepartmentPassPercentageRecord r2 = new DepartmentPassPercentageRecord(
                "CS", "SEM_1", "SUB_1", "OS", "A", 90
        );
        DepartmentPassPercentageRecord r3 = new DepartmentPassPercentageRecord(
                "EC", "SEM_2", "SUB_2", "DSP", "B", 70
        );

        assertThat(departmentPassPercentageRecord).isEqualTo(r2);
        assertThat(departmentPassPercentageRecord.hashCode()).isEqualTo(r2.hashCode());

        assertThat(departmentPassPercentageRecord).isNotEqualTo(r3);
    }

    @Test
    @DisplayName("toString() should include field names and values")
    void toString_ShouldContainFields() {
        DepartmentPassPercentageRecord departmentPassPercentageRecord = new DepartmentPassPercentageRecord(
                "CS", "SEM_1", "SUB_1", "OS", "A", 88
        );

        String str = departmentPassPercentageRecord.toString();

        assertThat(str).contains("DepartmentPassPercentageRecord");
        assertThat(str).contains("departmentName=CS");
        assertThat(str).contains("semesterId=SEM_1");
        assertThat(str).contains("subjectId=SUB_1");
        assertThat(str).contains("subjectName=OS");
        assertThat(str).contains("section=A");
        assertThat(str).contains("marks=88");
    }

    @Test
    @DisplayName("Null values should be stored for reference types")
    void nullValues_ShouldBeAllowed() {
        DepartmentPassPercentageRecord departmentPassPercentageRecord = new DepartmentPassPercentageRecord(
                null, null, null, null, null, 0
        );

        assertThat(departmentPassPercentageRecord.departmentName()).isNull();
        assertThat(departmentPassPercentageRecord.semesterId()).isNull();
        assertThat(departmentPassPercentageRecord.subjectId()).isNull();
        assertThat(departmentPassPercentageRecord.subjectName()).isNull();
        assertThat(departmentPassPercentageRecord.section()).isNull();
        assertThat(departmentPassPercentageRecord.marks()).isZero();
    }
}