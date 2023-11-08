package com.groupb.r2sproject.dtos.AuthDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    private String full_name;
    private String email;
    private String username;
    private String password;

}
