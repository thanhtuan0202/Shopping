package com.groupb.r2sproject.dtos.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductByCategory {
    private Long category_id;
    private String category_name;
    private Set<ProductResponse> products;
}
