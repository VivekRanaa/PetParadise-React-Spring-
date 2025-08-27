package com.petparadise.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petparadise.dto.BookingFormDTO;
import com.petparadise.dto.ProductDTO;
import com.petparadise.dto.ServiceDTO;
import com.petparadise.entity.BookingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
@ActiveProfiles("test")
public class PetParadiseIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testBookingFormEndpoints() throws Exception {
        setup();
        
        // Create a test booking
        BookingFormDTO bookingDTO = new BookingFormDTO();
        bookingDTO.setFirst_Name("John");
        bookingDTO.setLast_Name("Doe");
        bookingDTO.setEmail("john.doe@example.com");
        bookingDTO.setMobile_No("1234567890");
        bookingDTO.setPet_Name("Buddy");
        bookingDTO.setPet_Type("Dog");
        bookingDTO.setService_Type("Grooming");
        bookingDTO.setBooking_Date("2024-02-15");
        bookingDTO.setSpecial_Requests("Please be gentle");
        bookingDTO.setStatus(BookingStatus.PENDING);

        // Test creating a booking
        mockMvc.perform(post("/api/v1/Forms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.first_Name").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        // Test getting all bookings
        mockMvc.perform(get("/api/v1/Forms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());

        // Test searching bookings
        mockMvc.perform(get("/api/v1/Forms/search")
                .param("petName", "Buddy"))
                .andExpect(status().isOk());

        // Test health endpoint
        mockMvc.perform(get("/api/v1/Forms/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Booking Form Management is running!"));
    }

    @Test
    public void testProductEndpoints() throws Exception {
        setup();
        
        // Create a test product
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Dog Food");
        productDTO.setDescription("High-quality test dog food");
        productDTO.setPrice(new BigDecimal("29.99"));
        productDTO.setOriginalPrice(new BigDecimal("34.99"));
        productDTO.setCategory("Food");
        productDTO.setBrand("TestBrand");
        productDTO.setStockQuantity(100);
        productDTO.setIsActive(true);
        productDTO.setRating(4.5);
        productDTO.setReviewCount(50);

        // Test creating a product
        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Dog Food"))
                .andExpect(jsonPath("$.category").value("Food"));

        // Test getting all products
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());

        // Test searching products
        mockMvc.perform(get("/api/v1/products/search")
                .param("name", "Test"))
                .andExpect(status().isOk());

        // Test getting products by category
        mockMvc.perform(get("/api/v1/products/category/Food"))
                .andExpect(status().isOk());

        // Test getting product statistics
        mockMvc.perform(get("/api/v1/products/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalProducts").exists());

        // Test health endpoint
        mockMvc.perform(get("/api/v1/products/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product Management is running!"));
    }

    @Test
    public void testServiceEndpoints() throws Exception {
        setup();
        
        // Create a test service
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setName("Test Grooming Service");
        serviceDTO.setServiceType("Grooming");
        serviceDTO.setDescription("Professional grooming service for testing");
        serviceDTO.setPriceRange("$50 - $100");
        serviceDTO.setDuration("2 hours");
        serviceDTO.setIsActive(true);

        // Test creating a service
        mockMvc.perform(post("/api/v1/services")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(serviceDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Grooming Service"))
                .andExpect(jsonPath("$.serviceType").value("Grooming"));

        // Test getting all services
        mockMvc.perform(get("/api/v1/services"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());

        // Test searching services
        mockMvc.perform(get("/api/v1/services/search")
                .param("name", "Test"))
                .andExpect(status().isOk());

        // Test getting services by type
        mockMvc.perform(get("/api/v1/services/type/Grooming"))
                .andExpect(status().isOk());

        // Test getting active services
        mockMvc.perform(get("/api/v1/services/active"))
                .andExpect(status().isOk());

        // Test getting all service types
        mockMvc.perform(get("/api/v1/services/types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        // Test getting service statistics
        mockMvc.perform(get("/api/v1/services/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalServices").exists());

        // Test health endpoint
        mockMvc.perform(get("/api/v1/services/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Pet Service Management is running!"));
    }

    @Test
    public void testCorsConfiguration() throws Exception {
        setup();
        
        // Test CORS headers for React development servers
        mockMvc.perform(options("/api/v1/Forms")
                .header("Origin", "http://localhost:3000")
                .header("Access-Control-Request-Method", "POST")
                .header("Access-Control-Request-Headers", "Content-Type"))
                .andExpect(status().isOk());

        mockMvc.perform(options("/api/v1/products")
                .header("Origin", "http://localhost:5173")
                .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isOk());

        mockMvc.perform(options("/api/v1/services")
                .header("Origin", "http://localhost:5174")
                .header("Access-Control-Request-Method", "PUT"))
                .andExpect(status().isOk());
    }

    @Test
    public void testErrorHandling() throws Exception {
        setup();
        
        // Test getting non-existent booking
        mockMvc.perform(get("/api/v1/Forms/99999"))
                .andExpect(status().isNotFound());

        // Test getting non-existent product
        mockMvc.perform(get("/api/v1/products/99999"))
                .andExpect(status().isNotFound());

        // Test getting non-existent service
        mockMvc.perform(get("/api/v1/services/99999"))
                .andExpect(status().isNotFound());

        // Test creating booking with invalid data
        BookingFormDTO invalidBooking = new BookingFormDTO();
        // Missing required fields
        mockMvc.perform(post("/api/v1/Forms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidBooking)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPaginationAndSorting() throws Exception {
        setup();
        
        // Test pagination for bookings
        mockMvc.perform(get("/api/v1/Forms")
                .param("page", "0")
                .param("size", "5")
                .param("sortBy", "createdAt")
                .param("sortDir", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(5));

        // Test pagination for products
        mockMvc.perform(get("/api/v1/products")
                .param("page", "0")
                .param("size", "10")
                .param("sortBy", "name")
                .param("sortDir", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(10));

        // Test pagination for services
        mockMvc.perform(get("/api/v1/services")
                .param("page", "0")
                .param("size", "8")
                .param("sortBy", "serviceType")
                .param("sortDir", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(8));
    }
}
