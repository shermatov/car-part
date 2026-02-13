package com.shermatov.carparts.service.impl;

import com.shermatov.carparts.domain.Product;
import com.shermatov.carparts.domain.Shop;
import com.shermatov.carparts.dto.request.ProductRequest;
import com.shermatov.carparts.dto.response.ProductResponse;
import com.shermatov.carparts.mapper.ProductMapper;
import com.shermatov.carparts.repository.ProductRepository;
import com.shermatov.carparts.repository.ShopRepository;
import com.shermatov.carparts.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ProductResponse createProduct(Long shopId, ProductRequest request) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        Product product = productMapper.toEntity(request);
        product.setShop(shop);

        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productMapper.updateEntity(product, request);

        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        return  productMapper.
                toResponse(productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found")));

    }

    @Override
    public List<ProductResponse> getProductsByShop(Long shopId) {
        return productRepository.findByShopId(shopId)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
