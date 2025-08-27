package com.petparadise.service;

import com.petparadise.dto.ServiceDTO;
import com.petparadise.entity.Service;
import com.petparadise.repository.ServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class PetService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Create a new service
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Service service = modelMapper.map(serviceDTO, Service.class);
        Service savedService = serviceRepository.save(service);
        return convertToDTO(savedService);
    }

    // Get all services with pagination
    public Page<ServiceDTO> getAllServices(Pageable pageable) {
        Page<Service> services = serviceRepository.findAll(pageable);
        List<ServiceDTO> serviceDTOs = services.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(serviceDTOs, pageable, services.getTotalElements());
    }

    // Get service by ID
    public Optional<ServiceDTO> getServiceById(Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        return service.map(this::convertToDTO);
    }

    // Update service
    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        Optional<Service> existingService = serviceRepository.findById(id);
        if (existingService.isPresent()) {
            Service service = existingService.get();
            updateServiceFields(service, serviceDTO);
            Service updatedService = serviceRepository.save(service);
            return convertToDTO(updatedService);
        }
        throw new RuntimeException("Service not found with id: " + id);
    }

    // Delete service
    public void deleteService(Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Service not found with id: " + id);
        }
    }

    // Get services by service type
    public Page<ServiceDTO> getServicesByType(String serviceType, Pageable pageable) {
        Page<Service> services = serviceRepository.findByServiceType(serviceType, pageable);
        List<ServiceDTO> serviceDTOs = services.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(serviceDTOs, pageable, services.getTotalElements());
    }

    // Get active services
    public Page<ServiceDTO> getActiveServices(Pageable pageable) {
        Page<Service> services = serviceRepository.findByIsActiveTrue(pageable);
        List<ServiceDTO> serviceDTOs = services.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(serviceDTOs, pageable, services.getTotalElements());
    }

    // Search services by name
    public Page<ServiceDTO> searchServicesByName(String name, Pageable pageable) {
        Page<Service> services = serviceRepository.findByNameContaining(name, pageable);
        List<ServiceDTO> serviceDTOs = services.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(serviceDTOs, pageable, services.getTotalElements());
    }

    // Search services by multiple criteria
    public Page<ServiceDTO> searchServices(String serviceType, String name, Boolean isActive, Pageable pageable) {
        Page<Service> services = serviceRepository.findServicesByCriteria(serviceType, name, isActive, pageable);
        List<ServiceDTO> serviceDTOs = services.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(serviceDTOs, pageable, services.getTotalElements());
    }

    // Get all service types
    public List<String> getAllServiceTypes() {
        return serviceRepository.findAllServiceTypes();
    }

    // Get services by type and active status
    public List<ServiceDTO> getServicesByTypeAndStatus(String serviceType, Boolean isActive) {
        List<Service> services = serviceRepository.findByServiceTypeAndIsActive(serviceType, isActive);
        return services.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Toggle service active status
    public ServiceDTO toggleServiceStatus(Long id) {
        Optional<Service> existingService = serviceRepository.findById(id);
        if (existingService.isPresent()) {
            Service service = existingService.get();
            service.setIsActive(!service.getIsActive());
            Service updatedService = serviceRepository.save(service);
            return convertToDTO(updatedService);
        }
        throw new RuntimeException("Service not found with id: " + id);
    }

    // Get service statistics
    public ServiceStatistics getServiceStatistics() {
        long totalServices = serviceRepository.count();
        long activeServices = serviceRepository.countActiveServices();
        long inactiveServices = totalServices - activeServices;
        List<String> serviceTypes = serviceRepository.findAllServiceTypes();
        
        return new ServiceStatistics(totalServices, activeServices, inactiveServices, serviceTypes.size());
    }

    // Helper method to convert Entity to DTO
    private ServiceDTO convertToDTO(Service service) {
        ServiceDTO dto = modelMapper.map(service, ServiceDTO.class);
        if (service.getCreatedAt() != null) {
            dto.setCreatedAt(service.getCreatedAt().format(formatter));
        }
        if (service.getUpdatedAt() != null) {
            dto.setUpdatedAt(service.getUpdatedAt().format(formatter));
        }
        return dto;
    }

    // Helper method to update service fields
    private void updateServiceFields(Service existingService, ServiceDTO serviceDTO) {
        existingService.setName(serviceDTO.getName());
        existingService.setServiceType(serviceDTO.getServiceType());
        existingService.setDescription(serviceDTO.getDescription());
        existingService.setPriceRange(serviceDTO.getPriceRange());
        existingService.setDuration(serviceDTO.getDuration());
        existingService.setImageUrl(serviceDTO.getImageUrl());
        existingService.setIsActive(serviceDTO.getIsActive());
    }

    // Inner class for service statistics
    public static class ServiceStatistics {
        private final long totalServices;
        private final long activeServices;
        private final long inactiveServices;
        private final long totalServiceTypes;

        public ServiceStatistics(long totalServices, long activeServices, 
                               long inactiveServices, long totalServiceTypes) {
            this.totalServices = totalServices;
            this.activeServices = activeServices;
            this.inactiveServices = inactiveServices;
            this.totalServiceTypes = totalServiceTypes;
        }

        // Getters
        public long getTotalServices() { return totalServices; }
        public long getActiveServices() { return activeServices; }
        public long getInactiveServices() { return inactiveServices; }
        public long getTotalServiceTypes() { return totalServiceTypes; }
    }
}
