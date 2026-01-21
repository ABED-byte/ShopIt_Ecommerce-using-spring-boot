package com.AbedProjects.ShopIt.Dtos;

import com.AbedProjects.ShopIt.Order.OrderStatus;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderStatusRequest {

    @NotNull
    private OrderStatus orderStatus;

    // getters/setters
}
