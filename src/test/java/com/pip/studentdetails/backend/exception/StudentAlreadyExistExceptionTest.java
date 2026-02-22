package com.pip.studentdetails.backend.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentAlreadyExistExceptionTest {

    @Test
    void studentAlreadyExistExceptionMessage_Success() {
        String msg = "Student with id ST_090 already exists";
        StudentAlreadyExistsException ex = new StudentAlreadyExistsException(msg);

        assertEquals(msg, ex.getMessage());
    }

    @Test
    void shouldThrowStudentAlreadyExistsException() {
        assertThrows(StudentAlreadyExistsException.class, () -> {
            throw new StudentAlreadyExistsException("Student already exists");
        });
    }
}