package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.AuthDTO.RegisterDTO;
import com.groupb.r2sproject.dtos.UserDTO.GetProfile;
import com.groupb.r2sproject.dtos.UserDTO.UpdateProfile;

public interface UserService {
    public String login(String username, String password) throws Exception;
    public GetProfile register(RegisterDTO register) throws Exception;

    public GetProfile getUserByUserId(Long userId) throws Exception;

    public GetProfile updateProfile(Long userId, UpdateProfile profile) throws Exception;

    public Boolean changePassword(Long user_id, String old_password, String new_password, String confirm_password) throws Exception;
}
