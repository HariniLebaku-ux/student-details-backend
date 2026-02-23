package com.pip.studentdetails.backend.repository;

import com.pip.studentdetails.backend.entity.Student;
import com.pip.studentdetails.backend.entity.StudentDetails;
import com.pip.studentdetails.backend.entity.Department;
import com.pip.studentdetails.backend.entity.StudentDetailsId;
import com.pip.studentdetails.backend.entity.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class StudentDetailsRepositoryTest {

    @Autowired
    private StudentDetailsRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void insertStudent_ShouldInsertRow(){

        StudentDetailsId studentDetailsId= new StudentDetailsId("ST_018", "SUB_016");

        repository.insertStudentDetails("ST_018", "SUB_016",90);

        StudentDetails studentDetails = testEntityManager.find(StudentDetails.class, studentDetailsId);
        assertNotNull(studentDetails);
        assertEquals("ST_018", studentDetails.getStudent().getStudentId());
    }

    @Test
    void insertStudent_FailDuplicateKey() {

        repository.insertStudentDetails("ST_018", "SUB_016",90);

        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.insertStudentDetails("ST_018", "SUB_016",90);
        });
    }


}
