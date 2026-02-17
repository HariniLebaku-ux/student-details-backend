package com.pip.studentdetails.backend.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.pip.studentdetails.backend.response.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleDataIntegrityViolation_ShouldReturnBadRequest() {
        DataIntegrityViolationException ex =
                new DataIntegrityViolationException("duplicate key value violates unique constraint");

        ResponseEntity<ApiResponse<Void>> response = handler.handleDataIntegrityViolation(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(400);
        assertThat(response.getBody().message()).isEqualTo("Invalid Data");
    }

    @Test
    void handleMethodArgumentViolation_ShouldReturnBadRequest() throws Exception {
        // Build a minimal MethodArgumentNotValidException using mocks
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(
                /* parameter */ null,
                /* bindingResult */ mock(org.springframework.validation.BindingResult.class)
        );

        ResponseEntity<ApiResponse<Void>> response = handler.handleMethodArgumentViolation(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(400);
        assertThat(response.getBody().message()).isEqualTo("Invalid Data");
    }

    @Test
    void handleJsonProcessingException_ShouldReturnInternalServerError() {
        JsonProcessingException ex = JsonMappingException.fromUnexpectedIOE(new java.io.IOException("malformed"));

        ResponseEntity<ApiResponse<Void>> response = handler.handleJsonProcessingException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(500);
        assertThat(response.getBody().message()).isEqualTo("Error while processing Json record");
    }

    @Test
    void handleRuntimeException_ShouldReturnInternalServerError() {
        RuntimeException ex = new RuntimeException("boom");

        ResponseEntity<ApiResponse<Void>> response = handler.handleRuntimeException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(500);
        assertThat(response.getBody().message()).isEqualTo("Error while processing input request");
    }

    @Test
    void handleConstraintViolationException_ShouldReturnBadRequest() {
        ConstraintViolationException ex = new ConstraintViolationException("violation", /* violations */ null);

        ResponseEntity<ApiResponse<Void>> response = handler.handleConstraintViolationException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(400);
    }
}