package com.shermatov.carparts.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateShopRequest {

    @NotBlank
    private String name;

    private String address;
    private String phone;
    private String description;
}

