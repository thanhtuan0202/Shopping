package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.AuthDTO.RegisterDTO;
import com.groupb.r2sproject.dtos.UserDTO.GetProfile;
import com.groupb.r2sproject.dtos.UserDTO.UpdateProfile;

public interface UserService {
    String login(String username, String password) throws Exception;
    RegisterDTO register(RegisterDTO register) throws Exception;

    GetProfile getUserByUserId(Long userId) throws Exception;

    Boolean updateProfile(Long userId, UpdateProfile profile) throws Exception;
}
