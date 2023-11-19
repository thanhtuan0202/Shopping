package com.groupb.r2sproject.dtos.AdressDTO;

import com.groupb.r2sproject.entities.Address;
import lombok.Data;

import java.util.Set;

@Data
public class GetAdress {

    private Set<Address> addresses;

}
