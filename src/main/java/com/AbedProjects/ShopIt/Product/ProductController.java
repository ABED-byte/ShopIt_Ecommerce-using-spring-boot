package com.AbedProjects.ShopIt.Product;


import com.AbedProjects.ShopIt.Dtos.ProductRequestDto;
import com.AbedProjects.ShopIt.Dtos.ProductResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/addProduct")
    ResponseEntity<ProductResponseDto> addProducts(@Valid @RequestBody ProductRequestDto productRequestDto) {
       return ResponseEntity.ok( productService.addingProducts(productRequestDto));
    }

    @GetMapping("/getAllProducts")
    ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/getProductById/{id}")
    ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

}
