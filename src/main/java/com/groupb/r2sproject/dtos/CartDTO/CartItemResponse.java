package com.groupb.r2sproject.dtos.CartDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse
{
    private String p_name;
    private String size;
    private String color;
    private String model;
    private Float price;
    private LocalDate create_at;
    private Integer quantity;
    private Float total_price;
}
