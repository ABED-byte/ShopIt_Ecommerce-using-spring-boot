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
        private String Productname;
        private BigDecimal Productprice;
        private String category;
        private String Description;

        public ProductResponseDto(ProductEntity product) {
            this.id = product.getId();
            this.Productname = product.getProductName();
            this.Productprice = product.getProductPrice();
            this.category = product.getCategory();
            this.Description = product.getProductDescription();
        }
    }


