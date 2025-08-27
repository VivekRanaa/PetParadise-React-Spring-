package com.petparadise.controller;

import com.petparadise.dto.ServiceDTO;
import com.petparadise.service.PetService;
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
@RequestMapping("/api/services")
@Tag(name = "Pet Services", description = "API for managing pet care services")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174"})
public class ServiceController {

    @Autowired
    private PetService petService;

    @Operation(summary = "Create a new service", description = "Add a new pet care service")
    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@Valid @RequestBody ServiceDTO serviceDTO) {
        ServiceDTO createdService = petService.createService(serviceDTO);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all services", description = "Retrieve all pet care services with pagination")
    @GetMapping
    public ResponseEntity<Page<ServiceDTO>> getAllServices(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field") @RequestParam(defaultValue = "name") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<ServiceDTO> services = petService.getAllServices(pageable);
        return ResponseEntity.ok(services);
    }

    @Operation(summary = "Get service by ID", description = "Retrieve a specific service by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long id) {
        Optional<ServiceDTO> service = petService.getServiceById(id);
        return service.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update service", description = "Update an existing pet care service")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(
            @PathVariable Long id, 
            @Valid @RequestBody ServiceDTO serviceDTO) {
        try {
            ServiceDTO updatedService = petService.updateService(id, serviceDTO);
            return ResponseEntity.ok(updatedService);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete service", description = "Delete a pet care service")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        try {
            petService.deleteService(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get services by type", description = "Retrieve services by service type")
    @GetMapping("/type/{serviceType}")
    public ResponseEntity<Page<ServiceDTO>> getServicesByType(
            @PathVariable String serviceType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<ServiceDTO> services = petService.getServicesByType(serviceType, pageable);
        return ResponseEntity.ok(services);
    }

    @Operation(summary = "Get active services", description = "Retrieve only active services")
    @GetMapping("/active")
    public ResponseEntity<Page<ServiceDTO>> getActiveServices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<ServiceDTO> services = petService.getActiveServices(pageable);
        return ResponseEntity.ok(services);
    }

    @Operation(summary = "Search services by name", description = "Search services by name")
    @GetMapping("/search")
    public ResponseEntity<Page<ServiceDTO>> searchServices(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<ServiceDTO> services = petService.searchServicesByName(name, pageable);
        return ResponseEntity.ok(services);
    }

    @Operation(summary = "Advanced service search", description = "Search services with multiple criteria")
    @GetMapping("/advanced-search")
    public ResponseEntity<Page<ServiceDTO>> advancedSearch(
            @RequestParam(required = false) String serviceType,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                    Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<ServiceDTO> services = petService.searchServices(serviceType, name, isActive, pageable);
        return ResponseEntity.ok(services);
    }

    @Operation(summary = "Get services by type and status", description = "Retrieve services by type and active status")
    @GetMapping("/filter")
    public ResponseEntity<List<ServiceDTO>> getServicesByTypeAndStatus(
            @RequestParam String serviceType,
            @RequestParam Boolean isActive) {
        List<ServiceDTO> services = petService.getServicesByTypeAndStatus(serviceType, isActive);
        return ResponseEntity.ok(services);
    }

    @Operation(summary = "Toggle service status", description = "Toggle the active status of a service")
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<ServiceDTO> toggleServiceStatus(@PathVariable Long id) {
        try {
            ServiceDTO updatedService = petService.toggleServiceStatus(id);
            return ResponseEntity.ok(updatedService);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get all service types", description = "Retrieve all unique service types")
    @GetMapping("/types")
    public ResponseEntity<List<String>> getAllServiceTypes() {
        List<String> serviceTypes = petService.getAllServiceTypes();
        return ResponseEntity.ok(serviceTypes);
    }

    @Operation(summary = "Get service statistics", description = "Retrieve service statistics and metrics")
    @GetMapping("/statistics")
    public ResponseEntity<PetService.ServiceStatistics> getServiceStatistics() {
        PetService.ServiceStatistics stats = petService.getServiceStatistics();
        return ResponseEntity.ok(stats);
    }

    @Operation(summary = "Health check", description = "Check if the service management is running")
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Pet Service Management is running!");
    }
}
