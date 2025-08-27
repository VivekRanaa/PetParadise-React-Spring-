package com.petparadise.controller;

import com.petparadise.dto.BookingFormDTO;
import com.petparadise.entity.BookingStatus;
import com.petparadise.service.BookingFormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Forms")
@Tag(name = "Booking Forms", description = "API for managing pet service booking forms")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174"})
public class BookingFormController {

    @Autowired
    private BookingFormService bookingFormService;

    @Operation(summary = "Create a new booking", description = "Submit a new pet service booking form")
    @PostMapping
    public ResponseEntity<BookingFormDTO> createBooking(@Valid @RequestBody BookingFormDTO bookingFormDTO) {
        BookingFormDTO createdBooking = bookingFormService.createBooking(bookingFormDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all bookings", description = "Retrieve all booking forms with pagination")
    @GetMapping
    public ResponseEntity<Page<BookingFormDTO>> getAllBookings(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<BookingFormDTO> bookings = bookingFormService.getAllBookings(pageable);
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Get booking by ID", description = "Retrieve a specific booking by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookingFormDTO> getBookingById(@PathVariable Long id) {
        Optional<BookingFormDTO> booking = bookingFormService.getBookingById(id);
        return booking.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update booking", description = "Update an existing booking form")
    @PutMapping("/{id}")
    public ResponseEntity<BookingFormDTO> updateBooking(
            @PathVariable Long id, 
            @Valid @RequestBody BookingFormDTO bookingFormDTO) {
        try {
            BookingFormDTO updatedBooking = bookingFormService.updateBooking(id, bookingFormDTO);
            return ResponseEntity.ok(updatedBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update booking status", description = "Update the status of a booking")
    @PatchMapping("/{id}/status")
    public ResponseEntity<BookingFormDTO> updateBookingStatus(
            @PathVariable Long id, 
            @RequestParam BookingStatus status) {
        try {
            BookingFormDTO updatedBooking = bookingFormService.updateBookingStatus(id, status);
            return ResponseEntity.ok(updatedBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete booking", description = "Delete a booking form")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            bookingFormService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get bookings by email", description = "Retrieve all bookings for a specific email")
    @GetMapping("/by-email/{email}")
    public ResponseEntity<List<BookingFormDTO>> getBookingsByEmail(@PathVariable String email) {
        List<BookingFormDTO> bookings = bookingFormService.getBookingsByEmail(email);
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Get bookings by service", description = "Retrieve bookings for a specific service type")
    @GetMapping("/by-service/{serviceType}")
    public ResponseEntity<Page<BookingFormDTO>> getBookingsByService(
            @PathVariable String serviceType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<BookingFormDTO> bookings = bookingFormService.getBookingsByService(serviceType, pageable);
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Get bookings by status", description = "Retrieve bookings with a specific status")
    @GetMapping("/by-status/{status}")
    public ResponseEntity<Page<BookingFormDTO>> getBookingsByStatus(
            @PathVariable BookingStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<BookingFormDTO> bookings = bookingFormService.getBookingsByStatus(status, pageable);
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Search bookings", description = "Search bookings by various criteria")
    @GetMapping("/search")
    public ResponseEntity<Page<BookingFormDTO>> searchBookings(
            @RequestParam String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<BookingFormDTO> bookings = bookingFormService.searchBookings(searchTerm, pageable);
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Get recent bookings", description = "Retrieve bookings from the last 30 days")
    @GetMapping("/recent")
    public ResponseEntity<List<BookingFormDTO>> getRecentBookings() {
        List<BookingFormDTO> bookings = bookingFormService.getRecentBookings();
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Get booking statistics", description = "Retrieve booking statistics and metrics")
    @GetMapping("/statistics")
    public ResponseEntity<BookingFormService.BookingStatistics> getBookingStatistics() {
        BookingFormService.BookingStatistics stats = bookingFormService.getBookingStatistics();
        return ResponseEntity.ok(stats);
    }

    @Operation(summary = "Health check", description = "Check if the booking service is running")
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Booking Form Service is running!");
    }
}
