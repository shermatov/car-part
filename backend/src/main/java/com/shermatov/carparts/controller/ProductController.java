package com.shermatov.carparts.controller;

import com.shermatov.carparts.dto.request.ProductRequest;
import com.shermatov.carparts.dto.response.ProductResponse;
import com.shermatov.carparts.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a product for a shop
    @PostMapping("/shop/{shopId}")
    public ResponseEntity<ProductResponse> createProduct(
            @PathVariable Long shopId,
            @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(shopId, request));
    }

    // Update a product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(productId, request));
    }

    // Delete a product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Get products by shop
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<ProductResponse>> getProductsByShop(@PathVariable Long shopId) {
        return ResponseEntity.ok(productService.getProductsByShop(shopId));
    }
}
