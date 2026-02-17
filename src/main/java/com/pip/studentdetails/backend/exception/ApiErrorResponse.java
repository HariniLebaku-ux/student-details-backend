package com.pip.studentdetails.backend.exception;

/**
 * A simple DTO used for returning standardized error details
 * in API responses when exceptions occur.
 * This class is typically used in conjunction with
 * {@link org.springframework.web.bind.annotation.ExceptionHandler}
 * - status: The HTTP status code (e.g., 400, 404, 500)
 * - error: Short error label or type (e.g., "Bad Request", "Validation Error")
 * - message: Human-readable description of what went wrong
 * The class is intentionally kept immutable (no setters) to maintain
 * the integrity of the error response once created.
 */
public class ApiErrorResponse {
    /** HTTP status code associated with the error. */
    private int status;
    /** Short description or type of error (e.g., "Bad Request"). */
    private String error;
    /** Detailed error message for the client. */
    private String message;

    /**
     * Constructs an ApiErrorResponse with status, error, and message.
     * @param status  HTTP status code
     * @param error   A short name or label for the error
     * @param message Detailed explanation of the error
     */
    public ApiErrorResponse(int status, String error,String message) {
        this.status = status;
        this.error =error;
        this.message = message;
    }

    /** @return HTTP status code */
    public int getStatus() {
        return status;
    }

    /** @return short name or category of the error */
    public String getError() {
        return error;
    }

    /** @return detailed message explaining the error */
    public String getMessage() {
        return message;
    }
}