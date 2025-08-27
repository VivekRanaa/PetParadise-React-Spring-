package com.petparadise.repository;

import com.petparadise.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if user exists by email
    boolean existsByEmail(String email);

    // Find active users
    @Query("SELECT u FROM User u WHERE u.isActive = true")
    Page<User> findByIsActiveTrue(Pageable pageable);

    // Find users by name (case-insensitive)
    @Query("SELECT u FROM User u WHERE " +
           "LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<User> findByNameContaining(@Param("name") String name, Pageable pageable);

    // Find users by city
    Page<User> findByCity(String city, Pageable pageable);

    // Find users by state
    Page<User> findByState(String state, Pageable pageable);

    // Count active users
    @Query("SELECT COUNT(u) FROM User u WHERE u.isActive = true")
    Long countActiveUsers();

    // Search users
    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "u.phone LIKE CONCAT('%', :searchTerm, '%')")
    java.util.List<User> searchUsers(@Param("searchTerm") String searchTerm);

    // Find users with search functionality
    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "u.phone LIKE CONCAT('%', :search, '%')")
    Page<User> findBySearchTerm(@Param("search") String search, Pageable pageable);
}
