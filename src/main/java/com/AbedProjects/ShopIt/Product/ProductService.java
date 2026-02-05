package com.AbedProjects.ShopIt.Product;


import com.AbedProjects.ShopIt.Dtos.ProductRequestDto;
import com.AbedProjects.ShopIt.Dtos.ProductResponseDto;
import com.AbedProjects.ShopIt.Exception.BusinessValidationException;
import com.AbedProjects.ShopIt.Exception.ResourceNotFoundException;
import com.AbedProjects.ShopIt.Product.ProductEntity;
import com.AbedProjects.ShopIt.Product.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    public Page<ProductResponseDto> findAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ProductEntity> productEntities = productRepo.findAll(pageable);

        return productEntities.map(entity -> new ProductResponseDto(entity));

    }

    ProductResponseDto findProductById(Long id){
        ProductEntity productEntity = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("product not found"));

        return new ProductResponseDto(productEntity);
    }


    public ProductResponseDto addingProducts( ProductRequestDto dto) {


        ProductEntity product = ProductEntity.builder()
                .productName(dto.getProductname())
                .productDescription(dto.getDescription())
                .productPrice(dto.getProductprice())
                .category(dto.getCategory())
                .isActive(true)
                .stock(dto.getStock())
                .build();

        ProductEntity save = productRepo.save(product);

        return new ProductResponseDto(save);

    }

    public void deleteProductById(Long id) {
        if (!productRepo.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepo.deleteById(id);
    }

    @Transactional
    public ProductResponseDto updateProduct(ProductRequestDto dto) {

        ProductEntity product = productRepo.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found"
                ));

        product.setProductName(dto.getProductname());
        product.setProductDescription(dto.getDescription());
        product.setProductPrice(dto.getProductprice());
        product.setCategory(dto.getCategory());

        // no save() needed
        return new ProductResponseDto(product);
    }


    @Transactional
    public ProductEntity reduceStock(Long ProductId,int quantity){

        ProductEntity product = productRepo.findById(ProductId).orElseThrow(
                () -> new BusinessValidationException("Product not found with id: " + ProductId));

        if (quantity <= 0) {
            throw new BusinessValidationException("Invalid quantity");
        }

        if (product.getIsActive() != null && !product.getIsActive()) {
            throw new BusinessValidationException("Product is not active: " );
        }

        if (product.getProductPrice() == null) {
            throw new BusinessValidationException("Product has no price: ");
        }

        if (product.getStock()< quantity){
            throw new ResourceNotFoundException("out of stock");
        }

        product.setStock(product.getStock() - quantity);

        return product;

    }
}
