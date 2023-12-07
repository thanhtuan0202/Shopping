package com.groupb.r2sproject.dtos.OrderDTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewOrder {
    @NotBlank
    private String address;
    @NotBlank
    private String full_name;
    @NotBlank
    private String phone;
}
