package com.pip.studentdetails.backend.response;

public record PassPercentageReport(
    String departmentName,
    String semesterId,
    String subjectId,
    String subjectName,
    String section,
    double percentage
) {};