package com.groupb.r2sproject.dtos.UserDTO;

import com.groupb.r2sproject.entities.Address;
import com.groupb.r2sproject.entities.Cart;
import lombok.Data;

import java.util.Set;

@Data
public class GetProfile {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String full_name;
    private Set<Address> addresses;
}
