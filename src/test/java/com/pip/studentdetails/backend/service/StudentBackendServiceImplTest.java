package com.pip.studentdetails.backend.service;

import com.pip.studentdetails.backend.repository.StudentDetailsRepository;
import com.pip.studentdetails.backend.repository.StudentRepository;
import com.pip.studentdetails.backend.request.StudentDetailsRequest;
import com.pip.studentdetails.backend.request.StudentRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentBackendServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentDetailsRepository studentDetailsRepository;

    @InjectMocks
    private StudentBackendServiceImpl studentBackendServiceImpl;

    @Test
    void createStudent_Success() {
        StudentRequest request = new StudentRequest("ST_001", "John", "DEPT_CS", "A");
        studentBackendServiceImpl.createStudent(request);
        verify(studentRepository).insertStudent("ST_001", "John","DEPT_CS", "A");
    }

    @Test
    void createStudent_NullPointerException() {
        assertThrows(NullPointerException.class, 
                () -> studentBackendServiceImpl.createStudent(null));
    }

    @Test
    void createStudentDetails_Success() {
        StudentDetailsRequest request = new StudentDetailsRequest("ST_001", "SUB_001","John", 90);
        studentBackendServiceImpl.createStudentDetails(request);
        verify(studentDetailsRepository).insertStudentDetails("ST_001", "SUB_001", 90);
    }

    @Test
    void createStudentDetails_NullPointerException() {
        assertThrows(NullPointerException.class,
                () -> studentBackendServiceImpl.createStudentDetails(null),
                "Passing null to createStudentDetails should throw NullPointerException"
        );
    }

    @Test
    void getDepartmentTopperDetails_success() {
        String departmentId="DEPT_CS";
        List<Object[]> rows = List.of(
            new Object[]{"ST_001", "John", "SEM_1", "SUB_001", 100, "Computer Science and Engineering"},
            new Object[]{"ST_002", "sankar", "SEM_1", "SUB_001", 89, "Computer Science and Engineering"}
        );
        when(studentRepository.fetchStudentMarksForTopper(departmentId))
            .thenReturn(rows);

        String result=studentBackendServiceImpl.getDepartmentTopperDetails(departmentId);
        assertNotNull(result, "JSON result should not be null");
        assertTrue(result.contains("John"),  "Topper should be John");

        verify(studentRepository).fetchStudentMarksForTopper(departmentId);
    }

    @Test
    void getDepartmentTopperDetails_exception() {
        String departmentId="DEPT_CS";
        when(studentRepository.fetchStudentMarksForTopper(any()))
            .thenThrow(new RuntimeException("Exception Occurred"));
        assertThrows(RuntimeException.class, () -> 
            studentBackendServiceImpl.getDepartmentTopperDetails(departmentId));
    }

    @Test
    void getDepartmentWiseRankingDetails_success() {
        String departmentId="DEPT_CS";
        List<Object[]> rows = List.of(
                new Object[]{"ST_001", "John","A", "SEM_1", "SUB_001", 100, "Computer Science and Engineering"},
                new Object[]{"ST_002", "sankar","B","SEM_1", "SUB_001", 89, "Computer Science and Engineering"}
        );
        when(studentRepository.fetchStudentMarksForRanking(departmentId))
                .thenReturn(rows);

        String result=studentBackendServiceImpl.getDepartmentWiseRankingDetails(departmentId);
        assertNotNull(result, "JSON result should not be null");
        assertTrue(result.contains("John"), "Topper should be John");

        verify(studentRepository).fetchStudentMarksForRanking(departmentId);
    }

    @Test
    void getDepartmentWiseRankingDetails_exception() {
        String departmentId="DEPT_CS";
        when(studentRepository.fetchStudentMarksForRanking(any()))
                .thenThrow(new RuntimeException("Exception Occurred"));
        assertThrows(RuntimeException.class, () ->
                studentBackendServiceImpl.getDepartmentWiseRankingDetails(departmentId));
    }

    @Test
    void getDepartmentWisePassPercentageDetails_success() {
        List<Object[]> rows = List.of(
                new Object[]{"Computer Science and Engineering","SEM_1", "SUB_001","Physics","A", 100},
                new Object[]{"Computer Science and Engineering","SEM_1", "SUB_005","English","B", 76}
        );
        when(studentRepository.fetchDetailsForPassPercentage())
                .thenReturn(rows);
        String result=studentBackendServiceImpl.getDepartmentWisePassPercentageDetails();

        assertNotNull(result, "Response JSON should not be null");
        assertTrue(result.contains("Semester 1"), "Semester label should be present in the output");


        verify(studentRepository).fetchDetailsForPassPercentage();
    }

    @Test
    void getDepartmentWisePassPercentageDetails_exception() {
        when(studentRepository.fetchDetailsForPassPercentage())
                .thenThrow(new RuntimeException("Exception Occurred"));
        assertThrows(RuntimeException.class, () ->
                studentBackendServiceImpl.getDepartmentWisePassPercentageDetails());
    }

}
