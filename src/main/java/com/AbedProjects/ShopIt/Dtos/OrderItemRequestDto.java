package com.AbedProjects.ShopIt.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDto {

    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private Long quantity;
}

