package com.AbedProjects.ShopIt.Dtos;


import com.AbedProjects.ShopIt.Product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
    public class ProductResponseDto {

        private Long id;
        private String productname;
        private BigDecimal productprice;
        private String category;
        private String description;

        public ProductResponseDto(ProductEntity product) {
            this.id = product.getId();
            this.productname = product.getProductName();
            this.productprice = product.getProductPrice();
            this.category = product.getCategory();
            this.description = product.getProductDescription();
        }
    }


