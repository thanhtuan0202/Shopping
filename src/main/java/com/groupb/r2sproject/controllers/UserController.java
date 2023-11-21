package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.CustomUserDetail;
import com.groupb.r2sproject.dtos.OrderDTO.CreateOrderResponse;
import com.groupb.r2sproject.dtos.UserDTO.ChangePasswordDTO;
import com.groupb.r2sproject.dtos.UserDTO.GetProfile;
import com.groupb.r2sproject.dtos.UserDTO.UpdateProfile;
import com.groupb.r2sproject.services.CustomUserDetailServiceImplement;
import com.groupb.r2sproject.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private final CustomUserDetailServiceImplement userDetailsService;

    public UserController(CustomUserDetailServiceImplement userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @GetMapping()
    public ResponseEntity<?> getUserInfo() throws Exception {
        try{
            Long user_id = this.userDetailsService.getCurrentUserDetails().getUserId();
            GetProfile profile = userService.getUserByUserId(user_id);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateUserInfo(@RequestBody UpdateProfile profile) throws Exception {
        Long user_id_1 = this.userDetailsService.getCurrentUserDetails().getUserId();
        GetProfile result = userService.updateProfile(user_id_1, profile);
        if (result == null){
            return new ResponseEntity<String>("Cập nhật user thất bại", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<GetProfile>(result, HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<?> ChangePassword( @RequestBody ChangePasswordDTO dto) throws Exception {
        try{
            Long user_id = this.userDetailsService.getCurrentUserDetails().getUserId();
            System.out.println(user_id);
            Boolean res = this.userService.changePassword(user_id, dto.getOld_password(), dto.getNew_password(), dto.getConfirm_password());
            return new ResponseEntity<>("Cập nhật mật khẩu thành công", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
