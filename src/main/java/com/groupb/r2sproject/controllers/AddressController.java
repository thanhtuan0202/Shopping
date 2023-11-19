package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.AdressDTO.AddAddress;
import com.groupb.r2sproject.dtos.AdressDTO.GetAdress;
import com.groupb.r2sproject.dtos.AdressDTO.UpdateAddress;
import com.groupb.r2sproject.dtos.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.groupb.r2sproject.services.interfaces.AddressService;

import java.util.Objects;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getUserAddresses(@PathVariable("user_id") Long user_id, @RequestAttribute("current_user") CustomUserDetail user){
        if(!Objects.equals(user.getUserId(), user_id)){
            return new ResponseEntity<String>("Forbiden",null, 403);
        }
        GetAdress getAdress = addressService.getUserAddresses(user_id);
        return new ResponseEntity<GetAdress>(getAdress,  HttpStatus.OK);
    }

    @PostMapping("/user/{user_id}")
    public ResponseEntity<?> addUserAddresses(@PathVariable("user_id") Long user_id, @RequestAttribute("current_user") CustomUserDetail user, @RequestBody AddAddress addAdress){
        if(!Objects.equals(user.getUserId(), user_id)){
            return new ResponseEntity<String>("Forbiden",null, 403);
        }
        Boolean result = addressService.AddUserAddresses(user_id, addAdress);
        if (result == false){
            return new ResponseEntity<String>("Cập nhật address thất bại", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Cập nhật address thành công", HttpStatus.OK);
    }
    
    @PutMapping("/user/{user_id}")
    public ResponseEntity<?> updateUserAddresses(@PathVariable("user_id") Long user_id, @RequestBody UpdateAddress updateAddress, @RequestAttribute("current_user") CustomUserDetail user){
        if(!Objects.equals(user.getUserId(), user_id)){
            return new ResponseEntity<String>("Forbiden",null, 403);
        }
        Boolean result = addressService.UpdateUserAddresses(user_id, updateAddress);
        if (result == false){
            return new ResponseEntity<String>("Cập nhật address thất bại", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Cập nhật address thành công", HttpStatus.OK);
    }
    
    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<?> deleteUserAddresses(@PathVariable("user_id") Long user_id,@RequestBody UpdateAddress updateAddress, @RequestAttribute("current_user") CustomUserDetail user){
        if(!Objects.equals(user.getUserId(), user_id)){
            return new ResponseEntity<String>("Forbiden",null, 403);
        }
        Boolean result = addressService.DeleteAddresses(user_id, updateAddress);
        if (result == false){
            return new ResponseEntity<String>("Xóa address thất bại", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Xóa address thành công", HttpStatus.OK);
    }
    
}
