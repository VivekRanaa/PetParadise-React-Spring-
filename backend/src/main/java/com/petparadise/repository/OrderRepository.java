package com.petparadise.repository;

import com.petparadise.entity.Order;
import com.petparadise.entity.OrderStatus;
import com.petparadise.entity.PaymentStatus;
import com.petparadise.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find order by order number
    Optional<Order> findByOrderNumber(String orderNumber);

    // Find orders by user
    List<Order> findByUserOrderByOrderDateDesc(User user);
    Page<Order> findByUserOrderByOrderDateDesc(User user, Pageable pageable);
    Page<Order> findByUser(User user, Pageable pageable);

    // Find order by ID and user
    Optional<Order> findByIdAndUser(Long id, User user);

    // Find orders by user ID
    Page<Order> findByUserId(Long userId, Pageable pageable);

    // Find all orders
    List<Order> findAllByOrderByOrderDateDesc();
    Page<Order> findAllByOrderByOrderDateDesc(Pageable pageable);

    // Find orders by status
    List<Order> findByStatusOrderByOrderDateDesc(OrderStatus status);
    Page<Order> findByStatusOrderByOrderDateDesc(OrderStatus status, Pageable pageable);
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);

    // Find orders by payment status
    List<Order> findByPaymentStatusOrderByOrderDateDesc(PaymentStatus paymentStatus);
    Page<Order> findByPaymentStatusOrderByOrderDateDesc(PaymentStatus paymentStatus, Pageable pageable);
    Page<Order> findByPaymentStatus(PaymentStatus paymentStatus, Pageable pageable);

    // Find orders created between dates
    List<Order> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find orders by user and status
    Page<Order> findByUserAndStatus(User user, OrderStatus status, Pageable pageable);

    // Find orders with total amount greater than
    @Query("SELECT o FROM Order o WHERE o.totalAmount >= :minAmount")
    Page<Order> findByTotalAmountGreaterThanEqual(@Param("minAmount") BigDecimal minAmount, Pageable pageable);

    // Count orders by status
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(@Param("status") OrderStatus status);

    // Get total revenue
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.paymentStatus = 'PAID'")
    BigDecimal getTotalRevenue();

    // Get revenue for date range
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE " +
           "o.paymentStatus = 'PAID' AND o.orderDate BETWEEN :startDate AND :endDate")
    BigDecimal getRevenueByDateRange(@Param("startDate") LocalDateTime startDate, 
                                     @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE " +
           "o.paymentStatus = 'COMPLETED' AND o.createdAt BETWEEN :startDate AND :endDate")
    BigDecimal getRevenueForDateRange(@Param("startDate") LocalDateTime startDate, 
                                     @Param("endDate") LocalDateTime endDate);

    // Find recent orders by user
    @Query("SELECT o FROM Order o WHERE o.user = :user ORDER BY o.createdAt DESC")
    List<Order> findRecentOrdersByUser(@Param("user") User user, Pageable pageable);

    // Search orders
    @Query("SELECT o FROM Order o WHERE " +
           "LOWER(o.orderNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(o.user.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(o.user.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(o.user.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Order> searchOrders(@Param("searchTerm") String searchTerm);

    @Query("SELECT o FROM Order o WHERE " +
           "o.orderNumber LIKE CONCAT('%', :search, '%') OR " +
           "LOWER(CONCAT(o.user.firstName, ' ', o.user.lastName)) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(o.user.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Order> findBySearchTerm(@Param("search") String search, Pageable pageable);

    // Check if order exists for user
    boolean existsByUserAndOrderNumber(User user, String orderNumber);
}
