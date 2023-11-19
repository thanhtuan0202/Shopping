package com.groupb.r2sproject.dtos.UserDTO;

import com.groupb.r2sproject.entities.Address;
import com.groupb.r2sproject.entities.Cart;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetProfile {
    private Long id;
    private String username;
    private String email;
    private String full_name;
    private Set<String> addresses;
}
