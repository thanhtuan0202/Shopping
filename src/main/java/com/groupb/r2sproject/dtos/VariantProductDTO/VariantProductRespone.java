package com.groupb.r2sproject.dtos.VariantProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class VariantProductRespone {
    private Long id_product;
    private String name;	
    private List<VariantProductDTO> variantProducts; //list of variant products
    
	public VariantProductRespone() {
		super();
	}

	public VariantProductRespone(Long id_product, String name, List<VariantProductDTO> variantProducts) {
		super();
		this.id_product = id_product;
		this.name = name;
		this.variantProducts = variantProducts;
	}

	public Long getId_product() {
		return id_product;
	}

	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VariantProductDTO> getVariantProducts() {
		return variantProducts;
	}

	public void setVariantProducts(List<VariantProductDTO> variantProducts) {
		this.variantProducts = variantProducts;
	}
    
    
}
