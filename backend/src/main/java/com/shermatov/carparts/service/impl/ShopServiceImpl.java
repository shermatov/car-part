package com.shermatov.carparts.service.impl;


import com.shermatov.carparts.domain.Shop;
import com.shermatov.carparts.domain.User;
import com.shermatov.carparts.dto.request.ShopRequest;
import com.shermatov.carparts.dto.response.ShopResponse;
import com.shermatov.carparts.mapper.ShopMapper;
import com.shermatov.carparts.repository.ShopRepository;
import com.shermatov.carparts.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    @Override
    public ShopResponse createShop(ShopRequest request) {

        User owner = getCurrentUser();

        Shop shop = new Shop();
        shop.setOwner(owner);
        shop.setName(request.getName());
        shop.setDescription(request.getDescription());
        shop.setAddress(request.getAddress());

        shopRepository.save(shop);

        return shopMapper.toResponse(shop);
    }


    @Override
    public ShopResponse updateShop(Long shopId, ShopRequest request) {

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        shop.setName(request.getName());
        shop.setDescription(request.getDescription());
        shop.setAddress(request.getAddress());

        return shopMapper.toResponse(shopRepository.save(shop));
    }


    @Override
    public List<ShopResponse> getAllShops() {
        return shopRepository.findAll()
                .stream()
                .map(shopMapper::toResponse)
                .toList();
    }


    @Override
    public List<ShopResponse> getMyShops() {
        User user = getCurrentUser();
        return shopRepository.findByOwnerId(user.getId())
                .stream()
                .map(shopMapper::toResponse)
                .toList();
    }


    private User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
