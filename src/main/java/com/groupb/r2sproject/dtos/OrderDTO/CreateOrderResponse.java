package com.groupb.r2sproject.dtos.OrderDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
public class CreateOrderResponse {
    private Long id;
    private String address;
    private LocalDate delivery_time;
    private Float total_price;
    private Set<CustomProductInfo> cartLineItems;
}
