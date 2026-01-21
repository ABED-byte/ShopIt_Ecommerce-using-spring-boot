package com.AbedProjects.ShopIt.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private Long id;
    private String Productname;
    private BigDecimal Productprice;
    private String category;
    private String Description;
    private boolean active;
}
