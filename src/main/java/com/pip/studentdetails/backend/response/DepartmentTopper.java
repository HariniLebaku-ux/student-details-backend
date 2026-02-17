package com.pip.studentdetails.backend.response;

public record DepartmentTopper(
    String studentId,
    String studentName,
    String semesterId,
    String subjectTid,
    int marks,
    String departmentName
) {};