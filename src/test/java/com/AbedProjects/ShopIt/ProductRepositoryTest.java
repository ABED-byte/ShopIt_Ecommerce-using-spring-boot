package com.AbedProjects.ShopIt;


import com.AbedProjects.ShopIt.Product.ProductEntity;
import com.AbedProjects.ShopIt.Product.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.AssertionsForClassTypes.*;


@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepo productRepo;

    @Test
    void shouldReturnPagedProducts() {
        // Given
        for (int i = 0; i < 10; i++) {
            ProductEntity product = ProductEntity.builder()
                    .productName("Product " + i)
                    .stock(100)
                    .productPrice(java.math.BigDecimal.TEN)
                    .isActive(true)
                    .build();
            productRepo.save(product);
        }

        // When
        Pageable pageable = PageRequest.of(0, 5);
        Page<ProductEntity> page = productRepo.findAll(pageable);

        // Then
        assertThat(page.getTotalElements()).as("total elements should be 10").isEqualTo(10);
        assertThat(page).as("not be null").isNotNull();
        assertThat(page.getSize()).as("page size should be 5").isEqualTo(5);
        assertThat(page.getNumber()).as("page number should 0").isEqualTo(0);
        assertThat(page.getContent().size()).as("should return 5 items").isEqualTo(5);
        
    }
}
