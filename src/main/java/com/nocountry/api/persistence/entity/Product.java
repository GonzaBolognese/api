package com.nocountry.api.persistence.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name = "products")
@Getter @Setter
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String image;

    private int ranking;

    private int stock;

    private int time;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    public static enum ProductStatus{
        ENABLED, DISABLED
    }

}
