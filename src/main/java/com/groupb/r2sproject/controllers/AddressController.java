package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.AdressDTO.AddAddress;
import com.groupb.r2sproject.dtos.AdressDTO.GetAdress;
import com.groupb.r2sproject.dtos.AdressDTO.UpdateAddress;
import com.groupb.r2sproject.dtos.CustomUserDetail;
import com.groupb.r2sproject.services.CustomUserDetailServiceImplement;
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
    private final CustomUserDetailServiceImplement userDetailsService;

    public AddressController(CustomUserDetailServiceImplement userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @GetMapping()
    public ResponseEntity<?> getUserAddresses(){
        Long user_id = this.userDetailsService.getCurrentUserDetails().getUserId();
        GetAdress getAdress = addressService.getUserAddresses(user_id);
        return new ResponseEntity<GetAdress>(getAdress,  HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addUserAddresses(@RequestBody AddAddress addAdress){
        Long user_id = this.userDetailsService.getCurrentUserDetails().getUserId();
        Boolean result = addressService.AddUserAddresses(user_id, addAdress);
        if (!result){
            return new ResponseEntity<String>("Thêm address thất bại", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Thêm address thành công", HttpStatus.OK);
    }
    
    @PutMapping()
    public ResponseEntity<?> updateUserAddresses(@RequestBody UpdateAddress updateAddress){
        Long user_id = this.userDetailsService.getCurrentUserDetails().getUserId();
        Boolean result = addressService.UpdateUserAddresses(user_id, updateAddress);
        if (!result){
            return new ResponseEntity<String>("Cập nhật address thất bại", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Cập nhật address thành công", HttpStatus.OK);
    }
    
    @DeleteMapping("/{address_id}")
    public ResponseEntity<?> deleteUserAddresses(@PathVariable("address_id") Long address_id){
        Long user_id = this.userDetailsService.getCurrentUserDetails().getUserId();
        Boolean result = addressService.DeleteAddresses(user_id, address_id);
        if (!result){
            return new ResponseEntity<String>("Xóa address thất bại", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Xóa address thành công", HttpStatus.OK);
    }
    
}
