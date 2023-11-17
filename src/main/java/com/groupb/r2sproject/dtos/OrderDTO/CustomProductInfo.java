package com.groupb.r2sproject.dtos.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomProductInfo {
    private Long vp_id;
    private String name;
    private String color;
    private String size;
    private String model;
    private float price;
    private int quantity;
    private float total_price;
}
