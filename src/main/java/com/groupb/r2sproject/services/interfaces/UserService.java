package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.AuthDTO.RegisterDTO;

public interface UserService {
    String login(String username, String password) throws Exception;
    RegisterDTO register(RegisterDTO register) throws Exception;
}
