package com.petparadise.controller;

import com.petparadise.dto.CreateOrderRequest;
import com.petparadise.dto.UpdateOrderStatusRequest;
import com.petparadise.entity.Order;
import com.petparadise.entity.OrderStatus;
import com.petparadise.entity.User;
import com.petparadise.service.OrderService;
import com.petparadise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody CreateOrderRequest request,
                                         Authentication authentication) {
        try {
            User user = getCurrentUser(authentication);
            Order order = orderService.createOrderFromCart(user, request.getShippingAddress());
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating order: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders(Authentication authentication) {
        User user = getCurrentUser(authentication);
        List<Order> orders = orderService.getUserOrders(user);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Order>> getUserOrdersPaginated(Authentication authentication,
                                                              Pageable pageable) {
        User user = getCurrentUser(authentication);
        Page<Order> orders = orderService.getUserOrdersPaginated(user, pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId,
                                      Authentication authentication) {
        try {
            User user = getCurrentUser(authentication);
            Order order = orderService.getUserOrder(user, orderId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Order not found");
        }
    }

    // Admin endpoints
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/admin/paginated")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Order>> getAllOrdersPaginated(Pageable pageable) {
        Page<Order> orders = orderService.getAllOrdersPaginated(pageable);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/admin/{orderId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId,
                                               @Valid @RequestBody UpdateOrderStatusRequest request) {
        try {
            Order order = orderService.updateOrderStatus(orderId, request.getStatus());
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating order status: " + e.getMessage());
        }
    }

    @GetMapping("/admin/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> searchOrders(@RequestParam String searchTerm) {
        List<Order> orders = orderService.searchOrders(searchTerm);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/admin/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/admin/stats/revenue")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BigDecimal> getTotalRevenue() {
        BigDecimal revenue = orderService.getTotalRevenue();
        return ResponseEntity.ok(revenue != null ? revenue : BigDecimal.ZERO);
    }

    @GetMapping("/admin/stats/count")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> getTotalOrdersCount() {
        Long count = orderService.getTotalOrdersCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/admin/stats/count/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> getOrdersCountByStatus(@PathVariable OrderStatus status) {
        Long count = orderService.getOrdersCountByStatus(status);
        return ResponseEntity.ok(count);
    }

    private User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
