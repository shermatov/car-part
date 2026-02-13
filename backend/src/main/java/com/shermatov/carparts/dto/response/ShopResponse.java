package com.shermatov.carparts.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShopResponse {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String description;
}
