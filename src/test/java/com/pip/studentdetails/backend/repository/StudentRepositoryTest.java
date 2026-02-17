package com.pip.studentdetails.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.pip.studentdetails.backend.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void insertStudent_ShouldInsertRow(){

        repository.insertStudent("ST_031", "John","DEPT_CS", "A");

        Student student = testEntityManager.find(Student.class, "ST_031");
        assertNotNull(student);
        assertEquals("ST_031", student.getStudentId());
    }

    @Test
    void insertStudent_FailDuplicateKey() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.insertStudent("ST_001", "John","DEPT_CS", "A");
        });
    }

    @Test
    void fetchStudentMarksForTopper_Success() {
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
