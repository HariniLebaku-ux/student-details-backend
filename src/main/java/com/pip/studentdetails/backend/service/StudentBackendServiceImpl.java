package com.pip.studentdetails.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pip.studentdetails.backend.repository.StudentDetailsRepository;
import com.pip.studentdetails.backend.repository.StudentRepository;
import com.pip.studentdetails.backend.request.StudentDetailsRequest;
import com.pip.studentdetails.backend.request.StudentRequest;
import com.pip.studentdetails.backend.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service layer implementation for Student Backend operations.
 */
@Service("StudentBackendServiceImpl")
public class StudentBackendServiceImpl implements StudentBackendService {

    /**
     * Repository for Student table operations and Used to insert a student record into the database.
     */
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Repository for StudentDetails table operations and Used to insert student marks per subject.
     */
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    ObjectMapper mapper = new ObjectMapper();

    /**
     * Creates a Student record in the database.
     * studentRequest.studentId(), studentRequest.departmentId(), studentRequest.sectionId()
     */
    @Override
    public void createStudent(StudentRequest studentRequest) {
        // Passing record values to repository insert method
        studentRepository.insertStudent(studentRequest.studentId(),studentRequest.studentName(),studentRequest.departmentId(),studentRequest.sectionId());
    }

    /**
     * Creates StudentDetails record(s) in the database.
     */
    @Override
    public void createStudentDetails(StudentDetailsRequest studentDetailsRequest) {
        // Passing record values to repository insert method
        studentDetailsRepository.insertStudentDetails(studentDetailsRequest.studentId(), studentDetailsRequest.subjectId(), studentDetailsRequest.marks());
    }

    /**
     * Fetching the department topper list from database based on the departmentId
     * return the string object
     */
    @Override
    public String getDepartmentTopperDetails(String departmentId) {
        try {

        /**Fetch Raw Data from Database*/
        List<DepartmentTopper> departmentTopperDetails = studentRepository.fetchStudentMarksForTopper(departmentId)
                .stream()
                .map(r -> new DepartmentTopper(
                        r[0].toString(), r[1].toString(), r[2].toString(), r[3].toString(), ((Number) r[4]).intValue(), r[5].toString()
                )).toList();

        List<DepartmentTopperRecord> departmentTopperRecordList=departmentTopperDetails.stream()
                .collect(Collectors.groupingBy(DepartmentTopper::semesterId))
                .values().stream()
                .flatMap(departmentTopperListBySemester ->  {
                    List<DepartmentTopperRecord> departmentTopperRecords =
                            departmentTopperListBySemester.stream()
                                    .collect(Collectors.groupingBy(DepartmentTopper::studentId))
                                    .values().stream()
                                    .map(departmentTopperList -> {
                                        DepartmentTopper departmentTopper = departmentTopperList.get(0);
                                        int totalMarks = departmentTopperList.stream()
                                                .mapToInt(DepartmentTopper::marks)
                                                .sum();
                                        int totalSubjects = departmentTopperList.size();
                                        double percentage = Math.round((totalMarks/(totalSubjects*100.0))*10000)/100.0;
                                        return new DepartmentTopperRecord(
                                                departmentTopper.studentId(),
                                                departmentTopper.studentName(),
                                                percentage,
                                                "Semester " +departmentTopper.semesterId().replaceAll("\\D+", ""),
                                                departmentTopper.departmentName()
                                        );
                                    })
                                    .sorted(Comparator.comparingDouble(DepartmentTopperRecord::percentage).reversed())
                                .toList();

                   double maxPercentage = departmentTopperRecords.stream()
                        .mapToDouble(DepartmentTopperRecord::percentage)
                        .max()
                        .orElse(0);
                return departmentTopperRecords.stream()
                        .filter(r -> r.percentage() == maxPercentage);
                }).sorted(Comparator.comparing(DepartmentTopperRecord::semesterId)).toList();
            return mapper.writeValueAsString(departmentTopperRecordList);
        }
        catch(JsonProcessingException e) {
                throw new RuntimeException("Exception Occurred while processing" + e);
        }
    }

    /**
     * Fetching the department wise ranking list from database based on the departmentId
     * Who has < 40 then should be fail and record should be null
     * return the string object
     */
    public String getDepartmentWiseRankingDetails(String departmentId){
        try {
            /** Fetch raw rows from DB and map to domain rows*/
            List<StudentRanking> subjectRowsByStudent  = studentRepository.fetchStudentMarksForRanking(departmentId)
                    .stream().map(r -> new StudentRanking(r[0].toString(), r[1].toString(), r[2].toString(), r[3].toString(), r[4].toString(), ((Number) r[5]).intValue(), r[6].toString())).toList();

            List<StudentRankingRecord> rankedStudentsAcrossSemesters = subjectRowsByStudent .stream().collect(Collectors.groupingBy(StudentRanking::semesterId)).values().stream().flatMap(studentRankingRecordListBySemester -> {
                        List<StudentRankingRecord> listOfStudentRankingRecords = studentRankingRecordListBySemester.stream()
                                .collect(Collectors.groupingBy(StudentRanking::studentId))
                                .values().stream().map(studentRankingList -> {
                                    StudentRanking studentRanking = studentRankingList.get(0);
                                    boolean isFailed=studentRankingList.stream().anyMatch(s -> s.marks() < 40);
                                    int totalMarksForSemester  = studentRankingList.stream().mapToInt(StudentRanking::marks).sum();
                                    int subjectsForThisStudentInSemester  = studentRankingList.size();
                                    double percentage = Math.round((totalMarksForSemester / (subjectsForThisStudentInSemester * 100.0)) * 10000) / 100.0;
                                    return new StudentRankingRecord(0, studentRanking.studentId(), studentRanking.studentName(), studentRanking.section(), percentage, "Semester " + studentRanking.semesterId().replaceAll("\\D+", ""), studentRanking.departmentName(), isFailed
                                    );
                                }).sorted(Comparator.comparingDouble(StudentRankingRecord::percentage).reversed()).toList();

                        int currentRank  = 0;
                        double previousPercentage  = Double.NaN;
                        List<StudentRankingRecord> rankedInThisSemester = new ArrayList<>();
                        for (StudentRankingRecord r : listOfStudentRankingRecords) {
                            if (!r.isFailed()) {
                                if (Double.compare(r.percentage(), previousPercentage ) != 0) { currentRank ++; previousPercentage = r.percentage();
                                }
                                rankedInThisSemester.add(new StudentRankingRecord(currentRank, r.studentId(), r.studentName(), r.section(), r.percentage(), r.semesterId(), r.departmentName(), false));
                            }else{ rankedInThisSemester.add(new StudentRankingRecord(0, r.studentId(), r.studentName(), r.section(), r.percentage(), r.semesterId(), r.departmentName(), true));}
                        }
                        return rankedInThisSemester.stream();
                    }).sorted(Comparator.comparing(StudentRankingRecord::semesterId)).toList();

            List<Map<String, Object>> noRankedStudents = rankedStudentsAcrossSemesters.stream().map(r -> { Map<String, Object> m = new LinkedHashMap<>();
                        m.put("rank", r.rank() == 0 ? "NA" : String.valueOf(r.rank())); m.put("studentId", r.studentId()); m.put("studentName", r.studentName()); m.put("section", r.section()); m.put("percentage", r.percentage()); m.put("semesterId", r.semesterId()); m.put("departmentName", r.departmentName()); m.put("isFailed", r.isFailed());return m;
                    }).toList();
            return mapper.writeValueAsString(noRankedStudents);
        }catch(JsonProcessingException e) {throw new RuntimeException("Exception Occurred while processing" + e);
        }
    }

    /*
     * Fetch the details from student table for department-wise pass percentage
     */
    public String getDepartmentWisePassPercentageDetails() {
        try {
            List<DepartmentPassPercentageRecord> departmentPassPercentageRecordList = studentRepository.fetchDetailsForPassPercentage()
                    .stream().map(r -> new DepartmentPassPercentageRecord(r[0].toString(), r[1].toString(), r[2].toString(), r[3].toString(), r[4].toString(), ((Number) r[5]).intValue()
                    )).toList();
            List<PassPercentageReport> passPercenatgeReportList = departmentPassPercentageRecordList
                    .stream().collect(Collectors.groupingBy(departmentPassPercentageRecord ->
                            departmentPassPercentageRecord.departmentName() + "|" + departmentPassPercentageRecord.semesterId() + "|" + departmentPassPercentageRecord.subjectId() + "|" + departmentPassPercentageRecord.section()
                    )).entrySet().stream().map(departmentWisePassPercentageDetails -> {
                        List<DepartmentPassPercentageRecord> departmentPassPercentageRecords = departmentWisePassPercentageDetails.getValue();
                        int totalStudents = departmentPassPercentageRecords.size();
                        int passedStudents = (int) departmentPassPercentageRecords.stream()
                                .filter(departmentPassPercentageRecord -> departmentPassPercentageRecord.marks() >= 40)
                                .count();
                        double percentage = totalStudents == 0 ? 0 : (passedStudents * 100.0) / totalStudents;
                        percentage = Math.round(percentage * 100.0) / 100.0;
                        DepartmentPassPercentageRecord first = departmentPassPercentageRecords.get(0);
                        return new PassPercentageReport(first.departmentName(), "Semester " + first.semesterId().replaceAll("\\D+", ""), first.subjectId(), first.subjectName(), first.section(), percentage);
                    })
                    .sorted(Comparator.comparing(PassPercentageReport::departmentName).thenComparing(PassPercentageReport::semesterId).thenComparing(PassPercentageReport::subjectId).thenComparing(PassPercentageReport::section)
                    ).toList();
            return mapper.writeValueAsString(passPercenatgeReportList);
        } catch (JsonProcessingException e) {throw new RuntimeException("Exception Occurred while processing" + e); }
    }
}