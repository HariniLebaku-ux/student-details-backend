package com.pip.studentdetails.backend.response;

public record DepartmentTopperRecord(
        String studentId,
        String studentName,
        Double percentage,
        String semesterId,
        String departmentName
){};