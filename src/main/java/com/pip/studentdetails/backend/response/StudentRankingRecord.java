package com.pip.studentdetails.backend.response;

public record StudentRankingRecord(
        int rank,
        String studentId,
        String studentName,
        String section,
        Double percentage,
        String semesterId,
        String departmentName,
        boolean isFailed
){};