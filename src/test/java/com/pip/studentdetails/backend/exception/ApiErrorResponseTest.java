package com.pip.studentdetails.backend.exception;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApiErrorResponseTest {

    @Test
    void constructorAndGetters_ShouldWork() {
        // Arrange
        int status = 400;
        String error = "Bad Request";
        String message = "Invalid payload";

        // Act
        ApiErrorResponse resp = new ApiErrorResponse(status, error, message);

        // Assert
        assertThat(resp.getStatus()).isEqualTo(400);
        assertThat(resp.getError()).isEqualTo("Bad Request");
        assertThat(resp.getMessage()).isEqualTo("Invalid payload");
    }

    @Test
    void nullValues_ShouldBeAccepted() {
        ApiErrorResponse resp = new ApiErrorResponse(500, null, null);

        assertThat(resp.getStatus()).isEqualTo(500);
        assertThat(resp.getError()).isNull();
        assertThat(resp.getMessage()).isNull();
    }

    @Test
    void emptyStrings_ShouldBeReturnedAsIs() {
        ApiErrorResponse resp = new ApiErrorResponse(404, "", "");

        assertThat(resp.getStatus()).isEqualTo(404);
        assertThat(resp.getError()).isEmpty();
        assertThat(resp.getMessage()).isEmpty();
    }

    @Test
    void equalityContract_IsDefaultObjectEquality() {
        ApiErrorResponse a = new ApiErrorResponse(401, "Unauthorized", "Token missing");
        ApiErrorResponse b = new ApiErrorResponse(401, "Unauthorized", "Token missing");

        // Default Object equality (reference equality) -> not equal
        assertThat(a).isNotSameAs(b);
        assertThat(a.equals(b)).isFalse();
        assertThat(a.hashCode()).isNotEqualTo(b.hashCode());
    }
}
