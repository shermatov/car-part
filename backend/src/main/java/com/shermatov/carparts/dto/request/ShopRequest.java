package com.shermatov.carparts.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ShopRequest {

    @NotBlank(message = "Shop name is required")
    @Size(max = 150)
    private String name;

    @Size(max = 255)
    private String address;

    @Size(max = 30)
    private String phone;

    @Size(max = 500)
    private String description;
}

