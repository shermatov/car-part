package com.shermatov.carparts.service;



import com.shermatov.carparts.dto.request.ShopRequest;
import com.shermatov.carparts.dto.response.ShopResponse;

import java.util.List;

public interface ShopService {

    ShopResponse createShop(ShopRequest request);

    ShopResponse updateShop(Long shopId, ShopRequest request);

    List<ShopResponse> getAllShops();

    List<ShopResponse> getMyShops();
}

