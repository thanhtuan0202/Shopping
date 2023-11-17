package com.groupb.r2sproject.dtos.VariantProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VariantProductRespone {
    private Long id_product;
    private String name;	
    private List<VariantProductDTO> variantProducts; //list of variant products
}
