package com.pip.studentdetails.backend.response;

public record StudentRanking(
    String studentId,
    String studentName,
    String section,
    String semesterId,
    String subjectTid,
    int marks,
    String departmentName
) {};