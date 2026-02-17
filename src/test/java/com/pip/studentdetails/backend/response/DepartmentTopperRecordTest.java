package com.pip.studentdetails.backend.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class DepartmentTopperRecordTest {

    @Test
    void shouldCreateRecordAndExposeValues() {
        DepartmentTopperRecord r = new DepartmentTopperRecord(
                "ST_012",
                "Radha",
                92.5,
                "SEM_1",
                "Information Technology"
        );

        assertEquals("ST_012", r.studentId());
        assertEquals("Radha", r.studentName());
        assertEquals(92.5, r.percentage());
        assertEquals("SEM_1", r.semesterId());
        assertEquals("Information Technology", r.departmentName());
    }

    @Test
    void equalsAndHashCodeShouldWorkForSameValues() {
        DepartmentTopperRecord departmentTopperRecord1 = new DepartmentTopperRecord("ST_012", "Radha", 92.5, "SEM_1", "DEPT_IT");
        DepartmentTopperRecord departmentTopperRecord2 = new DepartmentTopperRecord("ST_012", "Radha", 92.5, "SEM_1", "DEPT_IT");

        assertThat(departmentTopperRecord1).isEqualTo(departmentTopperRecord2);
        assertThat(departmentTopperRecord1.hashCode()).isEqualTo(departmentTopperRecord2.hashCode());
    }
}
