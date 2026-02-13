package com.shermatov.carparts.dto.response;

import com.shermatov.carparts.domain.Brand;
import com.shermatov.carparts.domain.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Category category;
    private Brand brand;
}
