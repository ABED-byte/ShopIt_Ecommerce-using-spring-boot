package com.AbedProjects.ShopIt.Dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequestDto {

    @NotEmpty
    private List<@Valid OrderItemRequestDto> items;

}

