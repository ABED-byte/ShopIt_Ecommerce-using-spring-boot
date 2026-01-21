package com.AbedProjects.ShopIt.Product;


import com.AbedProjects.ShopIt.Dtos.ProductRequestDto;
import com.AbedProjects.ShopIt.Dtos.ProductResponseDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    List<ProductResponseDto> findAllProducts(){
        List<ProductEntity> all = productRepo.findAll();
        List<ProductResponseDto> collect = all.stream().map(product ->
                new ProductResponseDto(product)).collect(Collectors.toList()
        );

        return collect;
    }

    ProductResponseDto findProductById(Long id){
        ProductEntity productEntity = productRepo.findById(id).orElseThrow(() -> new RuntimeException("product not found"));

        return new ProductResponseDto(productEntity);
    }


    public ProductResponseDto addingProducts(@Valid ProductRequestDto dto) {


        ProductEntity product = ProductEntity.builder()
                .ProductName(dto.getProductname())
                .ProductDescription(dto.getDescription())
                .ProductPrice(dto.getProductprice())
                .Category(dto.getCategory())
                .isActive(true)
                .build();

        ProductEntity save = productRepo.save(product);

        return new ProductResponseDto(save);

    }
}
