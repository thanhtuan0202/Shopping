package com.groupb.r2sproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "variant_product")
@Getter
@Setter
public class VariantProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String color;

    @Column(length = 50, nullable = false)
    private String size;

    @Column(length = 100, nullable = false)
    private String model;

    @Column(nullable = false)
    private Float price;

    @OneToMany(mappedBy = "variant_product")
    private Set<CartLineItem> cartLineItems;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName="id", nullable = false)
    private Product product;
}
