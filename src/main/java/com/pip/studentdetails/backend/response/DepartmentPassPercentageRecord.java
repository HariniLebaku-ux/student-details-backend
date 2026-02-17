package com.pip.studentdetails.backend.response;

public record DepartmentPassPercentageRecord (
        String departmentName,
        String semesterId,
        String subjectId,
        String subjectName,
        String section,
        int marks
){};
