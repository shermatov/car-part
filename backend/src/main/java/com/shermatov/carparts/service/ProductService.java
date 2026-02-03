package com.shermatov.carparts.service;


import com.shermatov.carparts.dto.request.ProductRequest;
import com.shermatov.carparts.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(Long shopId, ProductRequest request);

    ProductResponse updateProduct(Long productId, ProductRequest request);

    void deleteProduct(Long productId);

    List<ProductResponse> getProductsByShop(Long shopId);

    List<ProductResponse> getAllProducts();
}
