package com.pip.studentdetails.backend.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentTopperTest {

    @Test
    void shouldCreateRecordAndExposeValues() {
        DepartmentTopper topper = new DepartmentTopper(
                "ST_001",
                "Radha",
                "SEM_1",
                "SUB_008",
                92,
                "Computer Science and Engineering"
        );

        assertEquals("ST_001", topper.studentId());
        assertEquals("Radha", topper.studentName());
        assertEquals("SEM_1", topper.semesterId());
        assertEquals("SUB_008", topper.subjectTid());   // as in your definition
        assertEquals(92, topper.marks());
        assertEquals("Computer Science and Engineering", topper.departmentName());
    }

    @Test
    void equalsAndHashCodeShouldWorkForSameValues() {
        DepartmentTopper topper1 = new DepartmentTopper("ST_001", "Radha", "SEM_1", "SUB_008", 92, "DEPT_CS");
        DepartmentTopper topper2 = new DepartmentTopper("ST_001", "Radha", "SEM_1", "SUB_008", 92, "DEPT_CS");

        assertEquals(topper1, topper2);
        assertEquals(topper1.hashCode(), topper2.hashCode());
    }

    @Test
    void equalsShouldDifferForDifferentValues() {
        DepartmentTopper topper1 = new DepartmentTopper("ST_001", "Radha", "SEM_1", "SUB_008", 92, "DEPT_CS");
        DepartmentTopper topper2 = new DepartmentTopper("ST_002", "Kiran", "SEM_1", "SUB_008", 92, "DEPT_CS");

        assertNotEquals(topper1, topper2);
    }

    @Test
    void toStringShouldContainFieldNamesAndValues() {
        DepartmentTopper topper = new DepartmentTopper("ST_001", "Radha", "SEM_1", "SUB_008", 92, "DEPT_CS");
        String s = topper.toString();

        assertTrue(s.contains("DepartmentTopper"));
        assertTrue(s.contains("studentId=ST_001"));
        assertTrue(s.contains("studentName=Radha"));
        assertTrue(s.contains("semesterId=SEM_1"));
        assertTrue(s.contains("subjectTid=SUB_008"));
        assertTrue(s.contains("marks=92"));
        assertTrue(s.contains("departmentName=DEPT_CS"));
    }

    @Test
    void canHandleNullStringsIfBusinessLogicAllows() {
        DepartmentTopper topper = new DepartmentTopper(null, null, null, null, 0, null);

        assertNull(topper.studentId());
        assertNull(topper.studentName());
        assertNull(topper.semesterId());
        assertNull(topper.subjectTid());
        assertEquals(0, topper.marks());
        assertNull(topper.departmentName());
    }

    @Test
    void negativeMarksAreAllowedUnlessYouValidateElsewhere() {
        DepartmentTopper topper = new DepartmentTopper("ST_009", "Test", "SEM_1", "SUB_001", -10, "DEPT_CS");
        assertEquals(-10, topper.marks());
    }
}