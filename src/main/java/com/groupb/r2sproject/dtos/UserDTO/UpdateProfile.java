package com.groupb.r2sproject.dtos.UserDTO;

import com.groupb.r2sproject.entities.Address;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateProfile {
    private String email;
    private String full_name;
}
