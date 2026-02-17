package com.pip.studentdetails.backend.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentRankingRecordTest {

    @Test
    void shouldCreateRecordAndExposeValues() {
        StudentRankingRecord r = new StudentRankingRecord(
                1,
                "ST_001",
                "Radha",
                "A",
                96.25,                              // Double autoboxing
                "SEM_1",
                "Computer Science and Engineering",
                true                                // isFailed
        );

        assertEquals(1, r.rank());
        assertEquals("ST_001", r.studentId());
        assertEquals("Radha", r.studentName());
        assertEquals("A", r.section());
        assertEquals(96.25, r.percentage());
        assertEquals("SEM_1", r.semesterId());
        assertEquals("Computer Science and Engineering", r.departmentName());
        assertTrue(r.isFailed());
    }

    @Test
    void equalsAndHashCodeShouldWorkForSameValues() {
        StudentRankingRecord a = new StudentRankingRecord(
                1, "ST_001", "Radha", "A", 96.25, "SEM_1", "DEPT_CS", false
        );
        StudentRankingRecord b = new StudentRankingRecord(
                1, "ST_001", "Radha", "A", 96.25, "SEM_1", "DEPT_CS", false
        );

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void equalsShouldDifferForDifferentValues() {
        StudentRankingRecord a = new StudentRankingRecord(
                1, "ST_001", "Radha", "A", 96.25, "SEM_1", "DEPT_CS", true
        );
        StudentRankingRecord c = new StudentRankingRecord(
                2, "ST_002", "Kiran", "B", 90.0, "SEM_1", "DEPT_CS", true
        );

        assertNotEquals(a, c);
    }

    @Test
    void toStringShouldContainFieldNamesAndValues() {
        StudentRankingRecord r = new StudentRankingRecord(
                5, "ST_010", "Meera", "B", 88.5, "SEM_2", "DEPT_CS", false
        );
        String s = r.toString();

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
        StudentRankingRecord r = new StudentRankingRecord(
                0, null, null, null, null, null, null, false
        );

        assertEquals(0, r.rank());
        assertNull(r.studentId());
        assertNull(r.studentName());
        assertNull(r.section());
        assertNull(r.percentage());     // requires Double (not double)
        assertNull(r.semesterId());
        assertNull(r.departmentName());
        assertFalse(r.isFailed());
    }

    @Test
    void percentageMayBeNaNOrInfinityUnlessValidatedElsewhere() {
        StudentRankingRecord r1 = new StudentRankingRecord(
                1, "ST_009", "Test", "A", Double.NaN, "SEM_1", "DEPT_CS", false
        );
        StudentRankingRecord r2 = new StudentRankingRecord(
                1, "ST_010", "Test2", "A", Double.POSITIVE_INFINITY, "SEM_1", "DEPT_CS", false
        );

        assertTrue(r1.percentage().isNaN());
        assertTrue(Double.isInfinite(r2.percentage()));
    }
}