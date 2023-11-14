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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Set<CartLineItem> getCartLineItems() {
		return cartLineItems;
	}

	public void setCartLineItems(Set<CartLineItem> cartLineItems) {
		this.cartLineItems = cartLineItems;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
}
