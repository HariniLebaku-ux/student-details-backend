package com.pip.studentdetails.backend.repository;

import com.pip.studentdetails.backend.constants.StudentQueryconstant;
import com.pip.studentdetails.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on the Student entity.
 * Additionally, it defines a custom native insert operation using a SQL constant.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    /**
     * Inserts a student record using a native SQL query.
     * - @Modifying is required because this is a DML operation (INSERT).
     * - @Transactional ensures the insert executes inside a transaction.
     */
    @Modifying
    @Transactional
    @Query(value= StudentQueryconstant.INSERT_STUDENT_QUERY,
        nativeQuery = true
    )
    void insertStudent(
            @Param("student_id") String student_id, @Param("student_name") String student_name,
            @Param("department_id") String department_id, @Param("section_id") String section_id
    );

    /**
     * Fetches topper student details for a given departmentId using a native query projection.
     */
    @Query(value= StudentQueryconstant.DEPARTMENT_TOPPER_QUERY,
        nativeQuery = true
    )
    List<Object[]> fetchStudentMarksForTopper(@Param("departmentId") String departmentId);

    /**
     * Fetches ranking details student details for a given departmentId using a native query projection.
     */
    @Query(value= StudentQueryconstant.DEPARTMENT_RANKING_QUERY,
        nativeQuery = true
    )
    List<Object[]> fetchStudentMarksForRanking(@Param("departmentId") String departmentId);

    /**
     * Fetches pass percentage details student details for a given departmentId using a native query projection.
     */
    @Query(value= StudentQueryconstant.DEPARTMENT_WISE_PASS_PERCENTAGE_QUERY,
            nativeQuery = true
    )
    List<Object[]> fetchDetailsForPassPercentage();
}
