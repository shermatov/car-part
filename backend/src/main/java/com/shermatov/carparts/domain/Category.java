package com.shermatov.carparts.domain;

import com.shermatov.carparts.domain.Base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter @Setter
@NoArgsConstructor
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;
}
