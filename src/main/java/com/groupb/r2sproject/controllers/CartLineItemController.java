package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.CartDTO.CartItemResponse;
import com.groupb.r2sproject.dtos.CustomUserDetail;
import com.groupb.r2sproject.services.CustomUserDetailServiceImplement;
import com.groupb.r2sproject.services.interfaces.CartLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/cart-lines")
public class CartLineItemController {
    @Autowired
    private CartLineItemService cartLineItemService;

    private final CustomUserDetailServiceImplement userDetailsService;

    public CartLineItemController(CustomUserDetailServiceImplement userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<?> getItemByUserId(
            @PathVariable("user_id") Long user_id
    ){
        if(!Objects.equals(user_id, this.userDetailsService.getCurrentUserDetails().getUserId())){
            return new ResponseEntity<String>("Forbiden",HttpStatus.FORBIDDEN);
        }
        Set<CartItemResponse> res = this.cartLineItemService.getItemByUserId(user_id);
        try{
            if(res == null){
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
