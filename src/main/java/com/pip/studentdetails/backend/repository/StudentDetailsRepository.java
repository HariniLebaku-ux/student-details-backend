package com.pip.studentdetails.backend.repository;

import com.pip.studentdetails.backend.constants.StudentQueryconstant;
import com.pip.studentdetails.backend.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for performing database operations on the StudentDetails entity.
 * Also includes a custom native SQL insert method for inserting student subject details.
 */
@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, String> {

    @Modifying
    @Transactional
    @Query(
            value= StudentQueryconstant.INSERT_STUDENT_DETAIL_QUERY,
            nativeQuery = true
    )
    void insertStudentDetails(
            @Param("student_id") String student_id,
            @Param("subject_id") String subject_id,
            @Param("marks") Integer marks
    );
}

