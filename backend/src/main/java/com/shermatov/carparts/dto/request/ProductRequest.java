package com.shermatov.carparts.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    private Long categoryId;
    private Long brandId;
}

