package com.pip.studentdetails.backend.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassPercentageReportTest {

    @Test
    void shouldCreateRecordAndExposeValues() {
        PassPercentageReport passPercentageReport = new PassPercentageReport(
                "Electronics and Communication Engineering",
                "SEM_1",
                "SUB_020",
                "ECE Subject 4",
                "A",
                85.5
        );

        assertEquals("Electronics and Communication Engineering", passPercentageReport.departmentName());
        assertEquals("SEM_1", passPercentageReport.semesterId());
        assertEquals("SUB_020", passPercentageReport.subjectId());
        assertEquals("ECE Subject 4", passPercentageReport.subjectName());
        assertEquals("A", passPercentageReport.section());
        assertEquals(85.5, passPercentageReport.percentage());
    }

    @Test
    void equalsAndHashCodeShouldWorkForSameValues() {
        PassPercentageReport passPercentageReport1 = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "A", 90.0);
        PassPercentageReport passPercentageReport2 = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "A", 90.0);

        assertEquals(passPercentageReport1, passPercentageReport2);
        assertEquals(passPercentageReport1.hashCode(), passPercentageReport2.hashCode());
    }

    @Test
    void equalsShouldDifferForDifferentValues() {
        PassPercentageReport passPercentageReport1 = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "A", 90.0);
        PassPercentageReport passPercentageReport2 = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "B", 90.0);

        assertNotEquals(passPercentageReport1, passPercentageReport2);
    }

    @Test
    void toStringShouldContainFieldNamesAndValues() {
        PassPercentageReport passPercentageReport2 = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_020", "ECE Sub4", "A", 90.25);
        String s = passPercentageReport2.toString();

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
        PassPercentageReport passPercentageReport = new PassPercentageReport(null, null, null, null, null, 0.0);

        assertNull(passPercentageReport.departmentName());
        assertNull(passPercentageReport.semesterId());
        assertNull(passPercentageReport.subjectId());
        assertNull(passPercentageReport.subjectName());
        assertNull(passPercentageReport.section());
        assertEquals(0.0, passPercentageReport.percentage());
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
        PassPercentageReport passPercentageReport = new PassPercentageReport("DEPT_ECE", "SEM_1", "SUB_1", "ECE Sub4", "A", 83.3333333);
        assertEquals(83.3333333, passPercentageReport.percentage(), 1e-9); // delta-based compare
    }
}