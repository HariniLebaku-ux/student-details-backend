package com.pip.studentdetails.backend.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DepartmentPassPercentageRecordTest {

    @Test
    @DisplayName("Accessor methods should return constructor values")
    void accessors_ShouldReturnValues() {
        DepartmentPassPercentageRecord record = new DepartmentPassPercentageRecord(
                "Computer Science and Engineering",
                "SEM_1",
                "SUB_001",
                "English",
                "A",
                85
        );

        assertThat(record.departmentName()).isEqualTo("Computer Science and Engineering");
        assertThat(record.semesterId()).isEqualTo("SEM_1");
        assertThat(record.subjectId()).isEqualTo("SUB_001");
        assertThat(record.subjectName()).isEqualTo("English");
        assertThat(record.section()).isEqualTo("A");
        assertThat(record.marks()).isEqualTo(85);
    }

    @Test
    @DisplayName("Record equality and hashCode should be structural")
    void equality_ShouldBeStructural() {
        DepartmentPassPercentageRecord r1 = new DepartmentPassPercentageRecord(
                "CS", "SEM_1", "SUB_1", "OS", "A", 90
        );
        DepartmentPassPercentageRecord r2 = new DepartmentPassPercentageRecord(
                "CS", "SEM_1", "SUB_1", "OS", "A", 90
        );
        DepartmentPassPercentageRecord r3 = new DepartmentPassPercentageRecord(
                "EC", "SEM_2", "SUB_2", "DSP", "B", 70
        );

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());

        assertThat(r1).isNotEqualTo(r3);
    }

    @Test
    @DisplayName("toString() should include field names and values")
    void toString_ShouldContainFields() {
        DepartmentPassPercentageRecord record = new DepartmentPassPercentageRecord(
                "CS", "SEM_1", "SUB_1", "OS", "A", 88
        );

        String str = record.toString();

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
        DepartmentPassPercentageRecord record = new DepartmentPassPercentageRecord(
                null, null, null, null, null, 0
        );

        assertThat(record.departmentName()).isNull();
        assertThat(record.semesterId()).isNull();
        assertThat(record.subjectId()).isNull();
        assertThat(record.subjectName()).isNull();
        assertThat(record.section()).isNull();
        assertThat(record.marks()).isZero();
    }
}