package com.groupb.r2sproject.entities;

import com.groupb.r2sproject.shared.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, columnDefinition = "text")
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column()
    private LocalDate delivery_time;

    @Column(nullable = false)
    private Float total_price;

    @Column(nullable = false)
    private OrderStatus status = OrderStatus.waiting;

    @OneToMany(mappedBy = "order")
    private Set<CartLineItem> cartLineItems;

    public Order(String address, LocalDate delivery_time, Float total_price, Set<CartLineItem> cartLineItems){
        this.address = address;
        this.delivery_time = delivery_time;
        this.total_price = total_price;
        this.cartLineItems = cartLineItems;
    }
}
