package com.pip.studentdetails.backend.response;

/**
 * A generic API response wrapper used for sending standardized responses from the backend to the client.
 * status: HTTP status code of the response (e.g., 200, 400, 500)
 * new ApiResponse<>(200, "Student created successfully");
 */
public record ApiResponse<T> (
     int status,
     String message
) {}