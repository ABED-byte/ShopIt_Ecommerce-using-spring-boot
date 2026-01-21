package com.AbedProjects.ShopIt.Dtos;

import com.AbedProjects.ShopIt.Order.OrderEntity;
import com.AbedProjects.ShopIt.Order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private Instant createdAt;

    public OrderResponseDto(OrderEntity order) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.orderStatus = order.getOrderStatus();
        this.totalAmount = order.getTotalAmount();
        this.createdAt = order.getCreatedAt();
    }
}

