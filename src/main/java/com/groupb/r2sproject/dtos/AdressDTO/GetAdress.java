package com.groupb.r2sproject.dtos.AdressDTO;

import com.groupb.r2sproject.entities.Address;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAdress {
    private Set<String> addresses;
}
