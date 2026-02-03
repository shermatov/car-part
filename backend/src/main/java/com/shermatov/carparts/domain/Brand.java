package com.shermatov.carparts.domain;

import com.shermatov.carparts.domain.Base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brands")
@Getter @Setter
@NoArgsConstructor
public class Brand extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;
}

