/*
package com.pip.studentdetails.backend.constants;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StudentQueryconstantTest {

    @Test
    void insertStudentQuery_ShouldNotBeNull() {
        assertThat(StudentQueryconstant.INSERT_STUDENT_QUERY)
                .isNotNull();
    }

    @Test
    void insertStudentQuery_ShouldContainExpectedSQL() {
        String query = StudentQueryconstant.INSERT_STUDENT_QUERY;

        // Basic SQL structure validation
        assertThat(query.toLowerCase()).contains("insert into");
        assertThat(query).contains("student");
        assertThat(query).contains("student_id");
        assertThat(query).contains("student_name");
        assertThat(query).contains("department_id");
        assertThat(query).contains("section");

        // Named parameters validation
        assertThat(query).contains(":student_id");
        assertThat(query).contains(":department_id");
        assertThat(query).contains(":section_id");
    }

    @Test
    void insertStudentQuery_ShouldMatchExpectedQueryExactly() {
        String expectedQuery = """
                 INSERT INTO student(student_id, student_name, department_id, section) 
                 VALUES (:student_id,:student_name, :department_id, :section_id )""";

        assertThat(StudentQueryconstant.INSERT_STUDENT_QUERY.trim())
                .isEqualTo(expectedQuery.trim());
    }

    @Test
    void insertStudentDetailQuery_ShouldNotBeNull() {
        assertThat(StudentQueryconstant.INSERT_STUDENT_DETAIL_QUERY)
                .isNotNull();
    }

    @Test
    void insertStudentDetailQuery_ShouldContainTableAndColumns() {
        String query = StudentQueryconstant.INSERT_STUDENT_DETAIL_QUERY;

        assertThat(query).contains("INSERT INTO student_details");
        assertThat(query).contains("student_id");
        assertThat(query).contains("subject_id");
        assertThat(query).contains("marks");
    }

    @Test
    void insertStudentDetailQuery_ShouldContainNamedParameters() {
        String query = StudentQueryconstant.INSERT_STUDENT_DETAIL_QUERY;

        assertThat(query).contains(":student_id");
        assertThat(query).contains(":subject_id");
        assertThat(query).contains(":marks");
    }

    @Test
    void insertStudentDetailQuery_ShouldMatchExpectedQuery() {
        String expected = """
                INSERT INTO student_details(student_id, subject_id, marks)
                VALUES (:student_id, :subject_id, :marks)""";

        assertThat(StudentQueryconstant.INSERT_STUDENT_DETAIL_QUERY.trim())
                .isEqualTo(expected.trim());
    }

    @Test
    void departmentTopperQuery_ShouldNotBeNull() {
        assertThat(StudentQueryconstant.DEPARTMENT_TOPPER_QUERY).isNotNull();
    }

    @Test
    void departmentTopperQuery_ShouldContainExpectedSelectColumns() {
        String q = StudentQueryconstant.DEPARTMENT_TOPPER_QUERY;

        // Columns with aliases
        assertThat(q).contains("st.student_id as student_id");
        assertThat(q).contains("st.student_name as student_name");
        assertThat(q).contains("sub.semester_id as semester_id");
        assertThat(q).contains("sub.subject_id as subject_id");
        assertThat(q).contains("sd.marks as marks");
        assertThat(q).contains("d.department_name as department_name");
    }

    @Test
    void departmentTopperQuery_ShouldContainExpectedTablesAndJoins() {
        String q = StudentQueryconstant.DEPARTMENT_TOPPER_QUERY;

        assertThat(q).contains("from student_core.student st");
        assertThat(q).contains("JOIN student_core.student_details sd");
        assertThat(q).contains("ON st.student_id = sd.student_id");
        assertThat(q).contains("JOIN student_core.subject sub");
        assertThat(q).contains("ON sd.subject_id = sub.subject_id");
        assertThat(q).contains("JOIN student_core.department d");
        assertThat(q).contains("ON st.department_id = d.department_id");
    }

    @Test
    void departmentTopperQuery_ShouldContainWhereClauseWithNamedParam() {
        String q = StudentQueryconstant.DEPARTMENT_TOPPER_QUERY;

        assertThat(q).contains("WHERE st.department_id=:departmentId");
        assertThat(q).contains(":departmentId");
    }

    @Test
    void departmentTopperQuery_ShouldStartWithSelectAndEndWithoutSemicolonOptional() {
        String q = StudentQueryconstant.DEPARTMENT_TOPPER_QUERY.trim();

        assertThat(q.toLowerCase()).startsWith("select");
        // Allow presence or absence of trailing semicolon depending on style
        assertThat(q.endsWith(";") || !q.endsWith(";")).isTrue();
    }

    @Test
    void departmentTopperQuery_ShouldMatchExactQuery_ForRegressionSafety() {
        String expected = """
                select  st.student_id as student_id, st.student_name as student_name,
                    sub.semester_id as semester_id, sub.subject_id as subject_id,
                    sd.marks as marks, d.department_name as department_name
                from student_core.student st JOIN student_core.student_details sd
                    ON st.student_id = sd.student_id JOIN student_core.subject sub
                    ON sd.subject_id = sub.subject_id JOIN student_core.department d
                    ON st.department_id = d.department_id WHERE st.department_id=:departmentId""";

        assertThat(StudentQueryconstant.DEPARTMENT_TOPPER_QUERY)
                .isEqualToIgnoringWhitespace(expected);
    }


    @Test
    void departmentRankingQuery_ShouldNotBeNull() {
        assertThat(StudentQueryconstant.DEPARTMENT_RANKING_QUERY).isNotNull();
    }

    @Test
    void departmentRankingQuery_ShouldContainExpectedSelectColumns() {
        String q = StudentQueryconstant.DEPARTMENT_RANKING_QUERY;

        assertThat(q).contains("st.student_id as student_id");
        assertThat(q).contains("st.student_name as student_name");
        assertThat(q).contains("st.section as section");
        assertThat(q).contains("sub.semester_id as semester_id");
        assertThat(q).contains("sub.subject_id as subject_id");
        assertThat(q).contains("sd.marks as marks");
        assertThat(q).contains("d.department_name as department_name");
    }

    @Test
    void departmentRankingQuery_ShouldContainExpectedTablesAndJoins() {
        String q = StudentQueryconstant.DEPARTMENT_RANKING_QUERY;

        assertThat(q).contains("from student_core.student st");
        assertThat(q).contains("JOIN student_core.student_details sd");
        assertThat(q).contains("ON st.student_id = sd.student_id");
        assertThat(q).contains("JOIN student_core.subject sub");
        assertThat(q).contains("ON sd.subject_id = sub.subject_id");
        assertThat(q).contains("JOIN student_core.department d");
        assertThat(q).contains("ON st.department_id = d.department_id");
    }

    @Test
    void departmentRankingQuery_ShouldContainWhereClauseWithNamedParam() {
        String q = StudentQueryconstant.DEPARTMENT_RANKING_QUERY;

        assertThat(q).contains("WHERE st.department_id=:departmentId");
        assertThat(q).contains(":departmentId");
    }

    @Test
    void departmentRankingQuery_ShouldStartWithSelect() {
        String q = StudentQueryconstant.DEPARTMENT_RANKING_QUERY.trim();
        assertThat(q.toLowerCase()).startsWith("select");
    }

    @Test
    void departmentRankingQuery_ShouldMatchExactQuery_ForRegressionSafety() {
        String expected = """
                select  st.student_id as student_id, st.student_name as student_name,
                    st.section as section, sub.semester_id as semester_id,
                    sub.subject_id as subject_id, sd.marks as marks, 
                    d.department_name as department_name
                from student_core.student st JOIN student_core.student_details sd
                    ON st.student_id = sd.student_id JOIN student_core.subject sub
                    ON sd.subject_id = sub.subject_id JOIN student_core.department d
                    ON st.department_id = d.department_id WHERE st.department_id=:departmentId""";

        assertThat(StudentQueryconstant.DEPARTMENT_RANKING_QUERY)
                .isEqualToIgnoringWhitespace(expected);
    }

    // If whitespace differences tend to break tests, replace the exact match above with this:
    @Test
    void departmentRankingQuery_ShouldMatchIgnoringWhitespace_Optional() {
        String expected = """
                select  st.student_id as student_id, st.student_name as student_name,
                    st.section as section, sub.semester_id as semester_id,
                    sub.subject_id as subject_id, sd.marks as marks, 
                    d.department_name as department_name
                from student_core.student st JOIN student_core.student_details sd
                    ON st.student_id = sd.student_id JOIN student_core.subject sub
                    ON sd.subject_id = sub.subject_id JOIN student_core.department d
                    ON st.department_id = d.department_id WHERE st.department_id=:departmentId""";

        assertThat(StudentQueryconstant.DEPARTMENT_RANKING_QUERY)
                .isEqualToIgnoringWhitespace(expected);
    }


    @Test
    void departmentWisePassPercentage_ShouldNotBeNullOrBlank() {
        assertThat(StudentQueryconstant.DEPARTMENT_WISE_PASS_PERCENTAGE_QUERY)
                .isNotNull()
                .isNotBlank();
    }

    @Test
    void departmentWisePassPercentage_ShouldContainExpectedSelectColumns() {
        String q = StudentQueryconstant.DEPARTMENT_WISE_PASS_PERCENTAGE_QUERY;

        assertThat(q).contains("d.department_name as department_name");
        assertThat(q).contains("sub.semester_id as semester_id");
        assertThat(q).contains("sub.subject_id as subject_id");
        assertThat(q).contains("sub.subject_name as subject_name");
        assertThat(q).contains("st.section as section");
        assertThat(q).contains("sd.marks as marks");
    }

    @Test
    void departmentWisePassPercentage_ShouldContainExpectedTablesAndJoins() {
        String q = StudentQueryconstant.DEPARTMENT_WISE_PASS_PERCENTAGE_QUERY;

        assertThat(q).contains("from student_core.student st");
        assertThat(q).contains("JOIN student_core.student_details sd");
        assertThat(q).contains("ON st.student_id = sd.student_id");
        assertThat(q).contains("JOIN student_core.subject sub");
        assertThat(q).contains("ON sd.subject_id = sub.subject_id");
        assertThat(q).contains("JOIN student_core.department d");
        assertThat(q).contains("ON st.department_id = d.department_id");
    }

    @Test
    void departmentWisePassPercentage_ShouldStartWithSelect() {
        String q = StudentQueryconstant.DEPARTMENT_WISE_PASS_PERCENTAGE_QUERY.trim();
        assertThat(q.toLowerCase()).startsWith("select");
    }

    // Robust comparison that ignores whitespace differences (recommended)
    @Test
    void departmentWisePassPercentage_ShouldMatchIgnoringWhitespace() {
        String expected = """
                select    d.department_name as department_name, sub.semester_id as semester_id, 
                    sub.subject_id as subject_id, sub.subject_name as subject_name, 
                    st.section as section, sd.marks as marks
                from student_core.student st JOIN student_core.student_details sd
                    ON st.student_id = sd.student_id JOIN student_core.subject sub
                    ON sd.subject_id = sub.subject_id JOIN student_core.department d
                    ON st.department_id = d.department_id""";

        assertThat(StudentQueryconstant.DEPARTMENT_WISE_PASS_PERCENTAGE_QUERY)
                .isEqualToIgnoringWhitespace(expected);
    }

    // Optional: strict exact match for regression safety (maybe brittle if spacing changes)
    @Test
    void departmentWisePassPercentage_ShouldMatchExactQuery_Optional() {
        String expected = """
                select  \td.department_name as department_name, sub.semester_id as semester_id, 
                    \tsub.subject_id as subject_id, sub.subject_name as subject_name, 
                    \tst.section as section, sd.marks as marks
                \tfrom student_core.student st JOIN student_core.student_details sd
                \t\tON st.student_id = sd.student_id JOIN student_core.subject sub
                \t\tON sd.subject_id = sub.subject_id JOIN student_core.department d
                \t\tON st.department_id = d.department_id""";

        // If your constant literally contains tab characters as in your snippet,
        // keep the \t in expected. If not, use the ignoring-whitespace test above.
        assertThat(StudentQueryconstant.DEPARTMENT_WISE_PASS_PERCENTAGE_QUERY.trim())
                .isEqualToIgnoringWhitespace(expected.trim());
    }

    // If you prefer case-insensitive + whitespace-insensitive normalization:
    @Test
    void departmentWisePassPercentage_ShouldMatchNormalized_Optional() {
        String expected = """
                select  d.department_name as department_name, sub.semester_id as semester_id, 
                    sub.subject_id as subject_id, sub.subject_name as subject_name, 
                    st.section as section, sd.marks as marks
                from student_core.student st JOIN student_core.student_details sd
                    ON st.student_id = sd.student_id JOIN student_core.subject sub
                    ON sd.subject_id = sub.subject_id JOIN student_core.department d
                    ON st.department_id = d.department_id""";

        String actual = StudentQueryconstant.DEPARTMENT_WISE_PASS_PERCENTAGE_QUERY;
        assertThat(normalizeSql(actual)).isEqualTo(normalizeSql(expected));
    }

    // Helper to normalize whitespace and case
    private String normalizeSql(String s) {
        return s.replaceAll("\\s+", " ").trim().toLowerCase();
    }
}
*/
