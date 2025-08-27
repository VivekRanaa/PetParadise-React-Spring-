package com.petparadise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    @NotNull
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Helper methods
    public BigDecimal getUnitPrice() {
        if (price != null) {
            return price;
        }
        if (product != null) {
            return product.getPrice();
        } else if (service != null) {
            // For services, we'll need to parse the price range or set a default price
            return new BigDecimal("100.00"); // Default service price
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotalPrice() {
        return getUnitPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public String getItemName() {
        if (product != null) {
            return product.getName();
        } else if (service != null) {
            return service.getName();
        }
        return "Unknown Item";
    }

    public String getItemType() {
        if (product != null) {
            return "PRODUCT";
        } else if (service != null) {
            return "SERVICE";
        }
        return "UNKNOWN";
    }

    public String getItemImageUrl() {
        if (product != null) {
            return product.getImageUrl();
        } else if (service != null) {
            return service.getImageUrl();
        }
        return null;
    }
}
