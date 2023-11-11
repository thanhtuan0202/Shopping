package com.groupb.r2sproject.dtos.OrderDTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewOrder {
    private String address;
    private LocalDate delivery_time;
}
