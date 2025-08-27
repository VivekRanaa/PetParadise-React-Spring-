package com.petparadise.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Clean integration test for PetParadise backend
 * Simple test to ensure Spring Boot context loads without errors
 */
@SpringBootTest
@ActiveProfiles("test")
public class PetParadiseCleanTest {

    @Test
    public void contextLoads() {
        // This test will pass if the application context loads successfully
    }
}
