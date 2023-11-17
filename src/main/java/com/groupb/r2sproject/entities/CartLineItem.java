package com.groupb.r2sproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "cart_line")
@Getter
@Setter
public class CartLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Float total_price;

    @Column(nullable = false)
    private ZonedDateTime create_at;

    @Column(nullable = false)
    private boolean is_delete;

    @ManyToOne()
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "variant_product_id", referencedColumnName = "id")
    private VariantProduct variant_product;

    public boolean getIs_Delete() {
        return this.is_delete;
    }

    public void setIs_Delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
}
