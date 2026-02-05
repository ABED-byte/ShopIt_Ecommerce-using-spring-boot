package com.AbedProjects.ShopIt.Product;


import com.AbedProjects.ShopIt.Dtos.ProductRequestDto;
import com.AbedProjects.ShopIt.Dtos.ProductResponseDto;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;



@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/foradmin/addproduct")
    ResponseEntity<ProductResponseDto> addProducts(@Valid @RequestBody ProductRequestDto productRequestDto) {
       return ResponseEntity.ok( productService.addingProducts(productRequestDto));
    }

    @GetMapping("/getallproducts")
    ResponseEntity<Page<ProductResponseDto>> getAllProducts(@RequestParam(defaultValue = "0")int pageNumber,@RequestParam(defaultValue = "6") int pageSize) {
        return ResponseEntity.ok(productService.findAllProducts(pageNumber,pageSize));
    }

    @GetMapping("/getProductById/{id}")
    ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/foradmin/deleteproduct/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/foradmin/updateproduct")
    ResponseEntity<ProductResponseDto> updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.updateProduct(productRequestDto));
    }

}