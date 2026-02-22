package com.pip.studentdetails.backend.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class DepartmentTopperRecordTest {

    @Test
    void shouldCreateRecordAndExposeValues() {
        DepartmentTopperRecord departmentTopperRecord = new DepartmentTopperRecord(
                "ST_012",
                "Radha",
                92.5,
                "SEM_1",
                "Information Technology"
        );

        assertEquals("ST_012", departmentTopperRecord.studentId());
        assertEquals("Radha", departmentTopperRecord.studentName());
        assertEquals(92.5, departmentTopperRecord.percentage());
        assertEquals("SEM_1", departmentTopperRecord.semesterId());
        assertEquals("Information Technology", departmentTopperRecord.departmentName());
    }

    @Test
    void equalsAndHashCodeShouldWorkForSameValues() {
        DepartmentTopperRecord departmentTopperRecord1 = new DepartmentTopperRecord("ST_012", "Radha", 92.5, "SEM_1", "DEPT_IT");
        DepartmentTopperRecord departmentTopperRecord2 = new DepartmentTopperRecord("ST_012", "Radha", 92.5, "SEM_1", "DEPT_IT");

        assertThat(departmentTopperRecord1).isEqualTo(departmentTopperRecord2);
        assertThat(departmentTopperRecord1.hashCode()).isEqualTo(departmentTopperRecord2.hashCode());
    }
}
