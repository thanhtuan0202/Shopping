package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("user_id") Long user_id){
        return null;
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<?> updateUserInfo(@PathVariable("user_id") Long user_id){
        return null;
    }
}
