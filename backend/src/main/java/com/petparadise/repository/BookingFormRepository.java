package com.petparadise.repository;

import com.petparadise.entity.BookingForm;
import com.petparadise.entity.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingFormRepository extends JpaRepository<BookingForm, Long> {

    // Find bookings by email
    List<BookingForm> findByEmail(String email);

    // Find bookings by mobile number
    @Query("SELECT b FROM BookingForm b WHERE b.mobile_No = :mobileNo")
    List<BookingForm> findByMobileNo(@Param("mobileNo") String mobileNo);

    // Find bookings by service type (facilities)
    List<BookingForm> findByFacilities(String facilities);

    // Find bookings by status
    List<BookingForm> findByStatus(BookingStatus status);

    // Find bookings by status with pagination
    Page<BookingForm> findByStatus(BookingStatus status, Pageable pageable);

    // Find bookings by service type with pagination
    Page<BookingForm> findByFacilities(String facilities, Pageable pageable);

    // Find bookings created between two dates
    List<BookingForm> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find bookings by customer name (case-insensitive)
    @Query("SELECT b FROM BookingForm b WHERE " +
           "LOWER(CONCAT(b.first_Name, ' ', b.last_Name)) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<BookingForm> findByCustomerNameContaining(@Param("name") String name);

    // Find bookings by location (state and pincode)
    List<BookingForm> findByStateAndPincode(String state, String pincode);

    // Find recent bookings (last 30 days)
    @Query("SELECT b FROM BookingForm b WHERE b.createdAt >= :thirtyDaysAgo ORDER BY b.createdAt DESC")
    List<BookingForm> findRecentBookings(@Param("thirtyDaysAgo") LocalDateTime thirtyDaysAgo);

    // Count bookings by status
    @Query("SELECT COUNT(b) FROM BookingForm b WHERE b.status = :status")
    Long countByStatus(@Param("status") BookingStatus status);

    // Count bookings by service type
    @Query("SELECT COUNT(b) FROM BookingForm b WHERE b.facilities = :facilities")
    Long countByFacilities(@Param("facilities") String facilities);

    // Find top customers by booking count
    @Query("SELECT b.email, COUNT(b) as bookingCount FROM BookingForm b " +
           "GROUP BY b.email ORDER BY bookingCount DESC")
    List<Object[]> findTopCustomersByBookingCount();

    // Check if customer exists by email
    boolean existsByEmail(String email);

    // Find bookings with search functionality
    @Query("SELECT b FROM BookingForm b WHERE " +
           "LOWER(b.first_Name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.last_Name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "b.mobile_No LIKE CONCAT('%', :search, '%') OR " +
           "LOWER(b.facilities) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<BookingForm> findBySearchTerm(@Param("search") String search, Pageable pageable);
}
