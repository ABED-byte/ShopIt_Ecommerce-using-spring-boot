package com.AbedProjects.ShopIt.Dtos;

import com.AbedProjects.ShopIt.OrderItem.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDto {
    private Long productId;
    private int quantity;

    public OrderItemResponseDto(OrderItemEntity entity) {
        this.productId = entity.getProductId();
        this.quantity = entity.getQuantity();
    }
}

