package com.petparadise.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class CreateOrderRequest {
    
    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;
    
    private String notes;
}
