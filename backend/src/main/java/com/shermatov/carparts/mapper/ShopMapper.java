package com.shermatov.carparts.mapper;

import com.shermatov.carparts.domain.Shop;
import com.shermatov.carparts.dto.request.ShopRequest;
import com.shermatov.carparts.dto.response.ShopResponse;
import org.springframework.stereotype.Component;


@Component
public class ShopMapper {

    public ShopResponse toResponse(Shop shop) {
        return ShopResponse.builder()
                .id(shop.getId())
                .name(shop.getName())
                .phone(shop.getPhone())
                .address(shop.getAddress())
                .description(shop.getDescription())
                .build();
    }


    public Shop toEntity(ShopRequest request) {
        if (request == null) return null;

        Shop shop = new Shop();
        apply(shop, request);
        return shop;
    }

    public void apply(Shop shop, ShopRequest request) {
        shop.setName(request.getName());
        shop.setAddress(request.getAddress());
        shop.setDescription(request.getDescription());
    }
}
