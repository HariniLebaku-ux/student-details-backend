package com.pip.studentdetails.backend.constants;

/** This class holds SQL query constants used by the backend repositories.*/
public class StudentQueryconstant {

    /**
     * SQL query for inserting a record into the student table.
     * This query is used in StudentRepository.insertStudent().
     */
    public static final String INSERT_STUDENT_QUERY="""
        INSERT INTO student(student_id, student_name, department_id, section) 
        VALUES (:student_id,:student_name, :department_id, :section_id )""";

    /**
     * SQL query for inserting a record into the student_details table.
     * This query is used in StudentDetailsRepository.insertStudentDetails().
     */
    public static final String INSERT_STUDENT_DETAIL_QUERY="""
        INSERT INTO student_details(student_id, subject_id, marks)
        VALUES (:student_id, :subject_id, :marks)""";

	/**
	 * SQL query for getting a record into the student table.
	 * This query is used in StudentRepository.fetchStudentMarksForTopper().
	 */
    public static final String DEPARTMENT_TOPPER_QUERY="""
    select  st.student_id as student_id, st.student_name as student_name,
	    sub.semester_id as semester_id, sub.subject_id as subject_id,
        sd.marks as marks, d.department_name as department_name
	from student_core.student st JOIN student_core.student_details sd
		ON st.student_id = sd.student_id JOIN student_core.subject sub
		ON sd.subject_id = sub.subject_id JOIN student_core.department d
		ON st.department_id = d.department_id WHERE st.department_id=:departmentId""";

	/**
	 * SQL query for getting a record into the student table.
	 * This query is used in StudentRepository.fetchStudentMarksForRanking().
	 */
    public static final String DEPARTMENT_RANKING_QUERY="""
    select  st.student_id as student_id, st.student_name as student_name,
		st.section as section, sub.semester_id as semester_id,
		sub.subject_id as subject_id, sd.marks as marks, 
		d.department_name as department_name
	from student_core.student st JOIN student_core.student_details sd
		ON st.student_id = sd.student_id JOIN student_core.subject sub
		ON sd.subject_id = sub.subject_id JOIN student_core.department d
		ON st.department_id = d.department_id WHERE st.department_id=:departmentId""";

	/**
	 * SQL query for getting a record into the student table.
	 * This query is used in StudentRepository.fetchDetailsForPassPercentage().
	 */
	public static final String DEPARTMENT_WISE_PASS_PERCENTAGE_QUERY= """
	select 	d.department_name as department_name, sub.semester_id as semester_id, 
		sub.subject_id as subject_id, sub.subject_name as subject_name, 
		st.section as section, sd.marks as marks
	from student_core.student st JOIN student_core.student_details sd
		ON st.student_id = sd.student_id JOIN student_core.subject sub
		ON sd.subject_id = sub.subject_id JOIN student_core.department d
		ON st.department_id = d.department_id""";
}
