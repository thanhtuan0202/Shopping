package com.groupb.r2sproject.dtos.VariantProductDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariantProductDTO {
    private Long id;
    private String color;
    private String size;
    private String model;
    private Double price;
}
