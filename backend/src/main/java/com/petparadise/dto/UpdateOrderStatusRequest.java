package com.petparadise.dto;

import com.petparadise.entity.OrderStatus;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class UpdateOrderStatusRequest {
    
    @NotNull(message = "Order status is required")
    private OrderStatus status;
}
