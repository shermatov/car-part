package com.shermatov.carparts.controller;

import com.shermatov.carparts.dto.request.ShopRequest;
import com.shermatov.carparts.dto.response.ShopResponse;
import com.shermatov.carparts.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    // Create a shop
    @PostMapping
    public ResponseEntity<ShopResponse> createShop(@RequestBody ShopRequest request) {
        return ResponseEntity.ok(shopService.createShop(request));
    }

    // Update a shop
    @PutMapping("/{shopId}")
    public ResponseEntity<ShopResponse> updateShop(
            @PathVariable Long shopId,
            @RequestBody ShopRequest request) {
        return ResponseEntity.ok(shopService.updateShop(shopId, request));
    }

    // Get all shops
    @GetMapping
    public ResponseEntity<List<ShopResponse>> getAllShops() {
        return ResponseEntity.ok(shopService.getAllShops());
    }

    // Get shops of current user
    @GetMapping("/my")
    public ResponseEntity<List<ShopResponse>> getMyShops() {
        return ResponseEntity.ok(shopService.getMyShops());
    }
    @DeleteMapping("{shopId}")
    public ResponseEntity<ShopResponse> deleteShop(@PathVariable Long shopId) {
        shopService.deleteShop(shopId);
        return ResponseEntity.ok().build();
    }
}
