package com.pip.studentdetails.backend.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentRankingRecordTest {

    @Test
    void shouldCreateRecordAndExposeValues() {
        StudentRankingRecord studentRankingRecord = new StudentRankingRecord(
                1,
                "ST_001",
                "Radha",
                "A",
                96.25,                              // Double autoboxing
                "SEM_1",
                "Computer Science and Engineering",
                true                                // isFailed
        );

        assertEquals(1, studentRankingRecord.rank());
        assertEquals("ST_001", studentRankingRecord.studentId());
        assertEquals("Radha", studentRankingRecord.studentName());
        assertEquals("A", studentRankingRecord.section());
        assertEquals(96.25, studentRankingRecord.percentage());
        assertEquals("SEM_1", studentRankingRecord.semesterId());
        assertEquals("Computer Science and Engineering", studentRankingRecord.departmentName());
        assertTrue(studentRankingRecord.isFailed());
    }

    @Test
    void equalsAndHashCodeShouldWorkForSameValues() {
        StudentRankingRecord studentRankingRecord1 = new StudentRankingRecord(
                1, "ST_001", "Radha", "A", 96.25, "SEM_1", "DEPT_CS", false
        );
        StudentRankingRecord studentRankingRecord2 = new StudentRankingRecord(
                1, "ST_001", "Radha", "A", 96.25, "SEM_1", "DEPT_CS", false
        );

        assertEquals(studentRankingRecord1, studentRankingRecord2);
        assertEquals(studentRankingRecord1.hashCode(), studentRankingRecord2.hashCode());
    }

    @Test
    void equalsShouldDifferForDifferentValues() {
        StudentRankingRecord studentRankingRecord1 = new StudentRankingRecord(
                1, "ST_001", "Radha", "A", 96.25, "SEM_1", "DEPT_CS", true
        );
        StudentRankingRecord studentRankingRecord2 = new StudentRankingRecord(
                2, "ST_002", "Kiran", "B", 90.0, "SEM_1", "DEPT_CS", true
        );

        assertNotEquals(studentRankingRecord1, studentRankingRecord2);
    }

    @Test
    void toStringShouldContainFieldNamesAndValues() {
        StudentRankingRecord studentRankingRecord = new StudentRankingRecord(
                5, "ST_010", "Meera", "B", 88.5, "SEM_2", "DEPT_CS", false
        );
        String s = studentRankingRecord.toString();

        // For records, default format is: StudentRankingRecord[rank=5, studentId=..., ...]
        assertTrue(s.contains("StudentRankingRecord"));
        assertTrue(s.contains("rank=5"));
        assertTrue(s.contains("studentId=ST_010"));
        assertTrue(s.contains("studentName=Meera"));
        assertTrue(s.contains("section=B"));
        assertTrue(s.contains("percentage=88.5"));
        assertTrue(s.contains("semesterId=SEM_2"));
        assertTrue(s.contains("departmentName=DEPT_CS"));
        assertTrue(s.contains("isFailed=false"));   // <--- include isFailed
    }

    @Test
    void nullStringsAndPercentageAreAllowedUnlessYouValidateElsewhere() {
        StudentRankingRecord studentRankingRecord = new StudentRankingRecord(
                0, null, null, null, null, null, null, false
        );

        assertEquals(0, studentRankingRecord.rank());
        assertNull(studentRankingRecord.studentId());
        assertNull(studentRankingRecord.studentName());
        assertNull(studentRankingRecord.section());
        assertNull(studentRankingRecord.percentage());     // requires Double (not double)
        assertNull(studentRankingRecord.semesterId());
        assertNull(studentRankingRecord.departmentName());
        assertFalse(studentRankingRecord.isFailed());
    }

    @Test
    void percentageMayBeNaNOrInfinityUnlessValidatedElsewhere() {
        StudentRankingRecord studentRankingRecord1 = new StudentRankingRecord(
                1, "ST_009", "Test", "A", Double.NaN, "SEM_1", "DEPT_CS", false
        );
        StudentRankingRecord studentRankingRecord2 = new StudentRankingRecord(
                1, "ST_010", "Test2", "A", Double.POSITIVE_INFINITY, "SEM_1", "DEPT_CS", false
        );

        assertTrue(studentRankingRecord1.percentage().isNaN());
        assertTrue(Double.isInfinite(studentRankingRecord2.percentage()));
    }
}