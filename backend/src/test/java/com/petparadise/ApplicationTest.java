package com.petparadise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Minimal test to verify Spring Boot application can start successfully
 */
@SpringBootTest
@ActiveProfiles("test")
public class ApplicationTest {

    @Test
    public void contextLoads() {
        // This test will pass if the application context loads successfully
    }
}
