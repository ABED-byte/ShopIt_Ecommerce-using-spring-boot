package com.AbedProjects.ShopIt.Dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequestDto {

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal totalAmount;
}

