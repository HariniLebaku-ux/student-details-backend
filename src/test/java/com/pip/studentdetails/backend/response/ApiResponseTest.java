package com.pip.studentdetails.backend.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApiResponseTest {

    @Test
    @DisplayName("Accessors should return constructor values")
    void accessors_ShouldReturnValues() {
        ApiResponse<Void> apiResponse = new ApiResponse<>(200, "OK");

        assertThat(apiResponse.status()).isEqualTo(200);
        assertThat(apiResponse.message()).isEqualTo("OK");
    }

    @Test
    @DisplayName("Equality should be structural (same components) and hashCode should match")
    void equalsAndHashCode_ShouldBeStructural() {
        ApiResponse<Void> a = new ApiResponse<>(404, "Not Found");
        ApiResponse<Void> b = new ApiResponse<>(404, "Not Found");
        ApiResponse<Void> c = new ApiResponse<>(500, "Server Error");

        assertThat(a).isEqualTo(b).hasSameHashCodeAs(b);
        assertThat(a).isNotEqualTo(c);
    }

    @Test
    @DisplayName("toString should contain component names and values")
    void toString_ShouldContainFields() {
        ApiResponse<Void> apiResponse = new ApiResponse<>(400, "Bad Request");

        String s = apiResponse.toString();
        assertThat(s).contains("ApiResponse");
        assertThat(s).contains("status=400");
        assertThat(s).contains("message=Bad Request");
    }

    @Test
    @DisplayName("Record should work with different generic types at compile-time")
    void generics_ShouldCompileAndWork() {
        ApiResponse<String> apiResponse1 = new ApiResponse<>(201, "Created");
        ApiResponse<Integer> apiResponse2 = new ApiResponse<>(202, "Accepted");

        assertThat(apiResponse1.status()).isEqualTo(201);
        assertThat(apiResponse1.message()).isEqualTo("Created");
        assertThat(apiResponse2.status()).isEqualTo(202);
        assertThat(apiResponse2.message()).isEqualTo("Accepted");
    }

    @Test
    @DisplayName("Null message should be accepted and preserved")
    void nullMessage_ShouldBeAllowed() {
        ApiResponse<Void> apiResponse = new ApiResponse<>(500, null);
        assertThat(apiResponse.message()).isNull();
    }
}