package com.groupb.r2sproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.groupb.r2sproject.services.interfaces.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getUserAddresses(@PathVariable("user_id") Long user_id){
        return null;
    }

    @PostMapping("/user/{user_id}")
    public ResponseEntity<?> addUserAddresses(@PathVariable("user_id") Long user_id){
        return null;
    }
    
    @PutMapping("/user/{user_id}/{address_id}")
    public ResponseEntity<?> updateUserAddresses(@PathVariable("user_id") Long user_id,@PathVariable("address_id") Long address_id){
        return null;
    }
    
    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<?> deleteUserAddresses(@PathVariable("user_id") Long user_id){
        return null;
    }
    
}
