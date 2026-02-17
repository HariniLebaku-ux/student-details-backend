package com.pip.studentdetails.backend.service;

import com.pip.studentdetails.backend.request.StudentDetailsRequest;
import com.pip.studentdetails.backend.request.StudentRequest;

/**
 * Backend business operations related to student management.
 *   1.Creating a student master record and Creating subject-wise student details (marks)
 *   2.Fetching department topper details (semester-wise) and department wise ranking
 */
public interface StudentBackendService {

    /**
     * Creates a new student entry in the system.
     * studentRequest Contains studentId, departmentId, and sectionId, used to insert a new student record.
     */
    void createStudent(StudentRequest studentRequest);

    /**
     * Creates a detailed student subject record (marks, subject, name, etc.).
     * studentDetailsRequest Contains subject-wise details of a student: studentId, subjectId, studentName, and marks.
     */
    void createStudentDetails(StudentDetailsRequest studentDetailsRequest);

    /**
     * return a list of DepartmentTopperRecord containing topper information for the department
     */
    String getDepartmentTopperDetails(String departmentId);

    /**
     * return a list of DepartmentWise Ranking Record based on the departmentID
     */
    String getDepartmentWiseRankingDetails(String departmentId);
    /**
     * return a list of DepartmentWise pass percentage Record containing topper information for the department
     */
    String getDepartmentWisePassPercentageDetails();
}
