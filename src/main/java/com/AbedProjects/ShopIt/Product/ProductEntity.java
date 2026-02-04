package com.AbedProjects.ShopIt.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

    private String category;

    private Boolean isActive;

    @Column(nullable = false)
    private int stock = 50;

    @Version
    private Long version;


}
