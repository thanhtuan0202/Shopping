package com.groupb.r2sproject.dtos.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateOrderResponse {
    private Long id;
    private String address;
    private LocalDate delivery_time;
    private Float total_price;
    private Set<CustomProductInfo> cartLineItems;
}
