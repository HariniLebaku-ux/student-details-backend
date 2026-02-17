package com.pip.studentdetails.backend.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentRankingTest {

    @Test
    void shouldCreateRecordAndExposeValues() {
        StudentRanking r = new StudentRanking(
                "ST_001",
                "Radha",
                "A",
                "SEM_1",
                "SUB_011",
                95,
                "Computer Science and Engineering"
        );

        assertEquals("ST_001", r.studentId());
        assertEquals("Radha", r.studentName());
        assertEquals("A", r.section());
        assertEquals("SEM_1", r.semesterId());
        assertEquals("SUB_011", r.subjectTid());
        assertEquals(95, r.marks());
        assertEquals("Computer Science and Engineering", r.departmentName());
    }

    @Test
    void equalsAndHashCodeShouldWorkForSameValues() {
        StudentRanking a = new StudentRanking("ST_001", "Radha", "A", "SEM_1", "SUB_011", 95, "DEPT_CS");
        StudentRanking b = new StudentRanking("ST_001", "Radha", "A", "SEM_1", "SUB_011", 95, "DEPT_CS");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void equalsShouldDifferForDifferentValues() {
        StudentRanking a = new StudentRanking("ST_001", "Radha", "A", "SEM_1", "SUB_011", 95, "DEPT_CS");
        StudentRanking c = new StudentRanking("ST_002", "Kiran", "B", "SEM_1", "SUB_011", 95, "DEPT_CS");

        assertNotEquals(a, c);
    }

    @Test
    void toStringShouldContainFieldNamesAndValues() {
        StudentRanking r = new StudentRanking("ST_001", "Radha", "A", "SEM_1", "SUB_011", 90, "DEPT_CS");
        String s = r.toString();

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
        StudentRanking r = new StudentRanking(null, null, null, null, null, 0, null);

        assertNull(r.studentId());
        assertNull(r.studentName());
        assertNull(r.section());
        assertNull(r.semesterId());
        assertNull(r.subjectTid());
        assertEquals(0, r.marks());
        assertNull(r.departmentName());
    }

    @Test
    void negativeMarksAreAllowedUnlessYouValidateElsewhere() {
        StudentRanking r = new StudentRanking("ST_009", "Test", "A", "SEM_1", "SUB_001", -7, "DEPT_CS");
        assertEquals(-7, r.marks());
    }
}
