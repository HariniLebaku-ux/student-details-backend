/*
package com.pip.studentdetails.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pip.studentdetails.backend.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void insertStudent_ShouldInsertRow(){
        Department department = new Department();

        department.setDepartmentId("DEPT_CS");
        department.setDepartmentName("Computer Science and Engineering");
        testEntityManager.persist(department);

        repository.insertStudent("ST_031", "John","DEPT_CS", "A");

        Student student = testEntityManager.find(Student.class, "ST_031");
        assertNotNull(student);
        assertEquals("ST_031", student.getStudentId());
    }

    @Test
    void insertStudent_FailDuplicateKey() {
        Department department = new Department();

        department.setDepartmentId("DEPT_CS");
        department.setDepartmentName("Computer Science and Engineering");
        testEntityManager.persist(department);

        repository.insertStudent("ST_031", "John","DEPT_CS", "A");

        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.insertStudent("ST_031", "John","DEPT_CS", "A");
        });
    }

    @Test
    void fetchStudentMarksForTopper_Success() {
        Department department = new Department();

        department.setDepartmentId("DEPT_CS");
        department.setDepartmentName("Computer Science and Engineering");
        testEntityManager.persist(department);

        Student student = new Student("ST_001", "John",department, "A");
        testEntityManager.persist(student);

        StudentDetailsId studentDetailsId = new StudentDetailsId("ST_001", "SUB_002");
        //testEntityManager.persist(studentDetailsId);
        Set<Subject> subjects1 = new HashSet<>();
        Semester semester = new Semester("SEM1", "Semester One",subjects1);
        testEntityManager.persist(semester);
        Subject subject = new Subject("SUB_001", "physics",semester);

        Set<Subject> subjects = new HashSet<>();
        subjects.add(subject);
        testEntityManager.persist(subject);

        StudentDetails studentDetails = new StudentDetails(studentDetailsId, student, subject,100);
        testEntityManager.persist(studentDetails);

        List<Object[]> departmentTopperMarksList = repository.fetchStudentMarksForTopper("DEPT_CS");

        assertThat(departmentTopperMarksList).isNotEmpty();

        Object[] departmentTopperMarksRecord = departmentTopperMarksList.get(0);
        assertThat(departmentTopperMarksRecord[0]).isNotNull();
        assertThat(departmentTopperMarksRecord[2]).isNotNull();
        assertThat(departmentTopperMarksRecord[3]).isNotNull();
        assertThat(departmentTopperMarksRecord[4]).isNotNull();
        assertThat(departmentTopperMarksRecord[5]).isEqualTo("Computer Science and Engineering");
    }

    @Test
    void fetchStudentMarksForTopper_EmptyResult() {
        List<Object[]> departmentTopperMarksList = repository.fetchStudentMarksForTopper("DEPT_INVALID");

        assertTrue(departmentTopperMarksList.isEmpty());
    }

    @Test
    void fetchStudentMarksForRanking_Success() {
        List<Object[]> studentMarksForRankingList = repository.fetchStudentMarksForRanking("DEPT_CS");

        assertThat(studentMarksForRankingList).isNotEmpty();

        Object[] studentMarksForRankingRecord = studentMarksForRankingList.get(0);
        assertThat(studentMarksForRankingRecord[0]).isNotNull();
        assertThat(studentMarksForRankingRecord[1]).isNotNull();
        assertThat(studentMarksForRankingRecord[2]).isNotNull();
        assertThat(studentMarksForRankingRecord[3]).isNotNull();
        assertThat(studentMarksForRankingRecord[4]).isNotNull();
        assertThat(studentMarksForRankingRecord[5]).isNotNull();
        assertThat(studentMarksForRankingRecord[6]).isEqualTo("Computer Science and Engineering");
    }

    @Test
    void fetchStudentMarksForRanking_EmptyResult() {
        List<Object[]> studentMarksForRankingList = repository.fetchStudentMarksForRanking("DEPT_INVALID");

        assertTrue(studentMarksForRankingList.isEmpty());
    }

    @Test
    void fetchDetailsForPassPercentage_Success() {
        List<Object[]> departmentWisePassPercentageList = repository.fetchDetailsForPassPercentage();

        assertThat(departmentWisePassPercentageList).isNotEmpty();

        Object[] departmentWisePassPercentageRecord = departmentWisePassPercentageList.get(0);
        assertThat(departmentWisePassPercentageRecord[0]).isIn("Computer Science and Engineering", "Electronics and Communication Engineering");
        assertThat(departmentWisePassPercentageRecord[1]).isNotNull();
        assertThat(departmentWisePassPercentageRecord[2]).isNotNull();
        assertThat(departmentWisePassPercentageRecord[3]).isNotNull();
        assertThat(departmentWisePassPercentageRecord[4]).isNotNull();
        assertThat(departmentWisePassPercentageRecord[5]).isNotNull();
    }
}
*/
