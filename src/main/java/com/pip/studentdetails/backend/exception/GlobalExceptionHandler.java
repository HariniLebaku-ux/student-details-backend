package com.pip.studentdetails.backend.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pip.studentdetails.backend.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * Global exception handler for the entire application.
 * Each method in this class handles a specific exception type
 * and returns a custom ApiResponse with appropriate status codes.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles database constraint violations such as:
     * Duplicate keys, Foreign key violations,Not-null constraint violations
     * Triggered when the database throws DataIntegrityViolationException.
     * @param ex The thrown DataIntegrityViolationException
     * @return A ResponseEntity containing standardized error response
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String msg="Invalid Data";

        ApiResponse<Void> response = new ApiResponse<>(
            HttpStatus.BAD_REQUEST.value(),
            msg
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * Handles validation failures triggered by @Valid or @Validated,
     * such as @NotBlank, @NotNull, @Size violations.
     * MethodArgumentNotValidException is thrown by Spring when request
     * body validation fails.
     * @param ex The thrown MethodArgumentNotValidException
     * @return A ResponseEntity containing standardized error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentViolation(MethodArgumentNotValidException ex) {
        String msg="Invalid Data";

        ApiResponse<Void> response = new ApiResponse<>(
            HttpStatus.BAD_REQUEST.value(),
            msg
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ApiResponse<Void>> handleJsonProcessingException(JsonProcessingException ex) {
        String msg="Error while processing Json record";

        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                msg
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException ex) {
        String msg="Error while processing input request";

        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                msg
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolationException(ConstraintViolationException ex) {
        String msg="Invalid DepartmentId";

        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                msg
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleStudentExists(StudentAlreadyExistsException ex) {

        ApiResponse<Void> body = new ApiResponse<>(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(body); // 409
    }

}