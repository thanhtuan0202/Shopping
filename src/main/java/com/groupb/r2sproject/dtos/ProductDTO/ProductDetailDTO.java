package com.groupb.r2sproject.dtos.ProductDTO;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private Long id;
    private String name;
    private String categoryName;
}
