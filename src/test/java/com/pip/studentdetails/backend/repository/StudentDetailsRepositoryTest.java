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

    private static final String STUDENT_ID = "ST_031";
    private static final String SUBJECT_ID = "SUB_007";

    @BeforeEach
    void setUpFkRows() {
        // 0) Ensure Department exists
        Department department = testEntityManager.find(Department.class, "DEPT_IT");
        if (department == null) {
            department = new Department();
            department.setDepartmentId("DEPT_IT");           // PK
            department.setDepartmentName("Information Tech"); // set NOT NULL columns
            testEntityManager.persist(department);
        }

        // 1) Ensure Student exists with all NOT NULL fields
        Student student = testEntityManager.find(Student.class, STUDENT_ID);
        if (student == null) {
            student = new Student();
            student.setStudentId(STUDENT_ID);
            student.setStudentName("Test Student"); // NOT NULL
            student.setSectionId("A");                // NOT NULL -> was missing earlier
            student.setDepartmentId(department);            // <-- set relation instead of departmentId
            testEntityManager.persist(student);
        }

        // 2) Ensure Subject exists
        Subject subject = testEntityManager.find(Subject.class, SUBJECT_ID);
        if (subject == null) {
            subject = new Subject();
            subject.setSubjectId(SUBJECT_ID);
            subject.setSubjectName("Mathematics"); // set NOT NULL fields
            testEntityManager.persist(subject);
        }

        testEntityManager.flush();
        testEntityManager.clear();
    }

    @Test
    void insertStudentDetails_ShouldInsertRow() {
        // when
        repository.insertStudentDetails(STUDENT_ID, SUBJECT_ID, 90);

        // then: fetch by composite key
        StudentDetailsId studentDetailsId = new StudentDetailsId(STUDENT_ID, SUBJECT_ID);
        StudentDetails studentDetails = testEntityManager.find(StudentDetails.class, studentDetailsId);

        assertNotNull(studentDetails, "StudentDetails row must be inserted");
        assertEquals(STUDENT_ID, studentDetails.getStudent().getStudentId());
        assertEquals(SUBJECT_ID, studentDetails.getSubject().getSubjectId());
        assertEquals(90, studentDetails.getMarks());
    }

    @Test
    void insertStudentDetails_FailDuplicateKey() {
        // given
        repository.insertStudentDetails(STUDENT_ID, SUBJECT_ID, 89);

        // when & then: inserting same composite key should violate PK/unique constraint
        assertThrows(DataIntegrityViolationException.class, () ->
                repository.insertStudentDetails(STUDENT_ID, SUBJECT_ID, 78)
        );
    }

    @Test
    void insertStudentDetails_FailsWhenStudentDoesNotExist() {
        // given a subject exists but student does not
        String missingStudentId = "ST_999";
        // make sure subject FK exists already (created in @BeforeEach)

        // when & then: FK violation expected
        assertThrows(DataIntegrityViolationException.class, () ->
                repository.insertStudentDetails(missingStudentId, SUBJECT_ID, 55)
        );
    }

    @Test
    void insertStudentDetails_FailsWhenSubjectDoesNotExist() {
        // given a student exists but subject does not
        String missingSubjectId = "SUB_999";

        // when & then: FK violation expected
        assertThrows(DataIntegrityViolationException.class, () ->
                repository.insertStudentDetails(STUDENT_ID, missingSubjectId, 61)
        );
    }
}
