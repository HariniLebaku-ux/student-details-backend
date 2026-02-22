package com.pip.studentdetails.backend.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentRankingTest {

    @Test
    void shouldCreateRecordAndExposeValues() {
        StudentRanking studentRanking = new StudentRanking(
                "ST_001",
                "Radha",
                "A",
                "SEM_1",
                "SUB_011",
                95,
                "Computer Science and Engineering"
        );

        assertEquals("ST_001", studentRanking.studentId());
        assertEquals("Radha", studentRanking.studentName());
        assertEquals("A", studentRanking.section());
        assertEquals("SEM_1", studentRanking.semesterId());
        assertEquals("SUB_011", studentRanking.subjectTid());
        assertEquals(95, studentRanking.marks());
        assertEquals("Computer Science and Engineering", studentRanking.departmentName());
    }

    @Test
    void equalsAndHashCodeShouldWorkForSameValues() {
        StudentRanking studentRanking1 = new StudentRanking("ST_001", "Radha", "A", "SEM_1", "SUB_011", 95, "DEPT_CS");
        StudentRanking studentRanking2 = new StudentRanking("ST_001", "Radha", "A", "SEM_1", "SUB_011", 95, "DEPT_CS");

        assertEquals(studentRanking1, studentRanking2);
        assertEquals(studentRanking1.hashCode(), studentRanking2.hashCode());
    }

    @Test
    void equalsShouldDifferForDifferentValues() {
        StudentRanking studentRanking1 = new StudentRanking("ST_001", "Radha", "A", "SEM_1", "SUB_011", 95, "DEPT_CS");
        StudentRanking studentRanking2 = new StudentRanking("ST_002", "Kiran", "B", "SEM_1", "SUB_011", 95, "DEPT_CS");

        assertNotEquals(studentRanking1, studentRanking2);
    }

    @Test
    void toStringShouldContainFieldNamesAndValues() {
        StudentRanking studentRanking = new StudentRanking("ST_001", "Radha", "A", "SEM_1", "SUB_011", 90, "DEPT_CS");
        String s = studentRanking.toString();

        assertTrue(s.contains("StudentRanking"));
        assertTrue(s.contains("studentId=ST_001"));
        assertTrue(s.contains("studentName=Radha"));
        assertTrue(s.contains("section=A"));
        assertTrue(s.contains("semesterId=SEM_1"));
        assertTrue(s.contains("subjectTid=SUB_011"));
        assertTrue(s.contains("marks=90"));
        assertTrue(s.contains("departmentName=DEPT_CS"));
    }

    @Test
    void nullStringsAreAllowedUnlessValidatedElsewhere() {
        StudentRanking studentRanking = new StudentRanking(null, null, null, null, null, 0, null);

        assertNull(studentRanking.studentId());
        assertNull(studentRanking.studentName());
        assertNull(studentRanking.section());
        assertNull(studentRanking.semesterId());
        assertNull(studentRanking.subjectTid());
        assertEquals(0, studentRanking.marks());
        assertNull(studentRanking.departmentName());
    }

    @Test
    void negativeMarksAreAllowedUnlessYouValidateElsewhere() {
        StudentRanking studentRanking = new StudentRanking("ST_009", "Test", "A", "SEM_1", "SUB_001", -7, "DEPT_CS");
        assertEquals(-7, studentRanking.marks());
    }
}
