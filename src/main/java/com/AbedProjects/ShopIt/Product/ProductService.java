package com.AbedProjects.ShopIt.Product;


import com.AbedProjects.ShopIt.Dtos.ProductRequestDto;
import com.AbedProjects.ShopIt.Dtos.ProductResponseDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    public ProductResponseDto addingProducts( ProductRequestDto dto) {


        ProductEntity product = ProductEntity.builder()
                .productName(dto.getProductname())
                .productDescription(dto.getDescription())
                .productPrice(dto.getProductprice())
                .category(dto.getCategory())
                .isActive(true)
                .build();

        ProductEntity save = productRepo.save(product);

        return new ProductResponseDto(save);

    }

    @Transactional
    public ProductResponseDto updateProduct(ProductRequestDto dto) {

        ProductEntity product = productRepo.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));

        product.setProductName(dto.getProductname());
        product.setProductDescription(dto.getDescription());
        product.setProductPrice(dto.getProductprice());
        product.setCategory(dto.getCategory());

        // no save() needed
        return new ProductResponseDto(product);
    }

}
