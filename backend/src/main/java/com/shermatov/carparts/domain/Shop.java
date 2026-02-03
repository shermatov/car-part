package com.shermatov.carparts.domain;

import com.shermatov.carparts.domain.Base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "shops",
        indexes = {
                @Index(name = "idx_shop_owner_id", columnList = "owner_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
// Shop represents a real-world business entity owned by a registered user.
public class Shop extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(length = 30)
    private String phone;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}

