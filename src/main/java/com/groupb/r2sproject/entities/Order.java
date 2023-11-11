package com.groupb.r2sproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "`orders`")
@Getter
@Setter
@NoArgsConstructor
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, columnDefinition = "text")
    private String address;

    @Column(nullable = false)
    private LocalDate delivery_time;

    @Column(nullable = false)
    private Float total_price;

    @OneToMany(mappedBy = "order")
    private Set<CartLineItem> cartLineItems;

    public Order(String address, LocalDate delivery_time, Float total_price, Set<CartLineItem> cartLineItems){
        this.address = address;
        this.delivery_time = delivery_time;
        this.total_price = total_price;
        this.cartLineItems = cartLineItems;
    }
}
