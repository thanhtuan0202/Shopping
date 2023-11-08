package com.groupb.r2sproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "`orders`")
@Getter
@Setter
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, columnDefinition = "text")
    private String address;

    @Column(nullable = false)
    private ZonedDateTime delivery_time;

    @Column(nullable = false)
    private Float total_price;

    @OneToMany(mappedBy = "order")
    private Set<CartLineItem> cartLineItems;
}
