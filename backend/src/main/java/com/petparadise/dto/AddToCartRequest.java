package com.petparadise.dto;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class AddToCartRequest {
    
    @NotNull(message = "Product ID or Service ID is required")
    private Long itemId;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    @NotNull(message = "Type is required (product or service)")
    private String type; // "product" or "service"
}
