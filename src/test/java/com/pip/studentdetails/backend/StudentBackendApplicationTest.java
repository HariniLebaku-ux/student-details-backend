package com.pip.studentdetails.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Verifies that the Spring ApplicationContext loads without errors.
 */
@SpringBootTest
@ActiveProfiles("test")
class StudentBackendApplicationTest {

    @Test
    void contextLoads() {
        // If the application context fails to start, this test will fail.
    }
}