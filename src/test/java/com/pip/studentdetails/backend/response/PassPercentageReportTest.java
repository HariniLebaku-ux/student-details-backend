package com.pip.studentdetails.backend.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassPercentageReportTest {

    @Test
    void shouldCreateRecordAndExposeValues() {
        PassPercentageReport r = new PassPercentageReport(
                "Electronics and Communication Engineering",
                "SEM_1",
                "SUB_020",
                "ECE Subject 4",
                "A",
                85.5
        );

        assertEquals("Electronics and Communication Engineering", r.departmentName());
        assertEquals("SEM_1", r.semesterId());
        assertEquals("SUB_020", r.subjectId());
        assertEquals("ECE Subject 4", r.subjectName());
        assertEquals("A", r.section());
        assertEquals(85.5, r.percentage());
    }

    @Test
    void equalsAndHashCodeShouldWorkForSameValues() {
        PassPercentageReport a = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "A", 90.0);
        PassPercentageReport b = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "A", 90.0);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void equalsShouldDifferForDifferentValues() {
        PassPercentageReport a = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "A", 90.0);
        PassPercentageReport c = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "B", 90.0);

        assertNotEquals(a, c);
    }

    @Test
    void toStringShouldContainFieldNamesAndValues() {
        PassPercentageReport r = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "A", 90.25);
        String s = r.toString();

        assertTrue(s.contains("PassPercentageReport"));
        assertTrue(s.contains("departmentName=DEPT_ECE"));
        assertTrue(s.contains("semesterId=SEM_1"));
        assertTrue(s.contains("subjectId=SUB_020"));
        assertTrue(s.contains("subjectName=ECE Sub4"));
        assertTrue(s.contains("section=A"));
        assertTrue(s.contains("percentage=90.25"));
    }

    @Test
    void nullStringsAreAllowedUnlessValidatedElsewhere() {
        PassPercentageReport r = new PassPercentageReport(null, null, null, null, null, 0.0);

        assertNull(r.departmentName());
        assertNull(r.semesterId());
        assertNull(r.subjectId());
        assertNull(r.subjectName());
        assertNull(r.section());
        assertEquals(0.0, r.percentage());
    }

    @Test
    void percentageSupportsNaNAndInfinityUnlessYouForbidIt() {
        PassPercentageReport nan = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_1", "ECE Sub4", "A", Double.NaN);
        PassPercentageReport inf = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_1", "ECE Sub4", "A", Double.POSITIVE_INFINITY);

        assertTrue(Double.isNaN(nan.percentage()));
        assertTrue(Double.isInfinite(inf.percentage()));
    }

    @Test
    void floatingPointPrecisionComparison() {
        PassPercentageReport r = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_1", "ECE Sub4", "A", 83.3333333);
        assertEquals(83.3333333, r.percentage(), 1e-9); // delta-based compare
    }
}