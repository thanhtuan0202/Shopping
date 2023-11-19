package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.AuthDTO.LoginDTO;
import com.groupb.r2sproject.dtos.AuthDTO.RegisterDTO;
import com.groupb.r2sproject.dtos.UserDTO.GetProfile;
import com.groupb.r2sproject.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registrationDTO) {
        try{
            GetProfile result = userService.register(registrationDTO);
            if (result != null) {
                return new ResponseEntity<GetProfile>(result, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<RegisterDTO>((RegisterDTO) null, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try{
            String token = userService.login(loginDTO.getUsername(),loginDTO.getPassword());
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
