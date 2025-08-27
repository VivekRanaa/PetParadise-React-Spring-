package com.petparadise.repository;

import com.petparadise.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    // Find services by service type
    List<Service> findByServiceType(String serviceType);

    // Find services by service type with pagination
    Page<Service> findByServiceType(String serviceType, Pageable pageable);

    // Find active services
    List<Service> findByIsActiveTrue();

    // Find active services with pagination
    Page<Service> findByIsActiveTrue(Pageable pageable);

    // Find services by name containing (case-insensitive)
    @Query("SELECT s FROM Service s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Service> findByNameContaining(@Param("name") String name);

    // Find services by name containing with pagination
    @Query("SELECT s FROM Service s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Service> findByNameContaining(@Param("name") String name, Pageable pageable);

    // Search services by multiple criteria
    @Query("SELECT s FROM Service s WHERE " +
           "(:serviceType IS NULL OR s.serviceType = :serviceType) AND " +
           "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:isActive IS NULL OR s.isActive = :isActive)")
    Page<Service> findServicesByCriteria(
            @Param("serviceType") String serviceType,
            @Param("name") String name,
            @Param("isActive") Boolean isActive,
            Pageable pageable);

    // Count services by service type
    @Query("SELECT COUNT(s) FROM Service s WHERE s.serviceType = :serviceType")
    Long countByServiceType(@Param("serviceType") String serviceType);

    // Count active services
    @Query("SELECT COUNT(s) FROM Service s WHERE s.isActive = true")
    Long countActiveServices();

    // Get all unique service types
    @Query("SELECT DISTINCT s.serviceType FROM Service s WHERE s.serviceType IS NOT NULL ORDER BY s.serviceType")
    List<String> findAllServiceTypes();

    // Find services by service type and active status
    List<Service> findByServiceTypeAndIsActive(String serviceType, Boolean isActive);

    // Search services by description containing
    @Query("SELECT s FROM Service s WHERE LOWER(s.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Service> findByDescriptionContaining(@Param("keyword") String keyword);
}
