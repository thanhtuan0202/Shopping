package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.CartDTO.AddNewProduct;
import com.groupb.r2sproject.dtos.CustomUserDetail;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.exceptions.NotFoundException;
import com.groupb.r2sproject.services.interfaces.CartLineItemService;
import com.groupb.r2sproject.services.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartLineItemService cartLineItemService;

    @GetMapping("/{cart_id}")
    public ResponseEntity<?> getCartById(@PathVariable("cart_id") Long cart_id){
        return null;
    }
    
    @GetMapping("/{user_id}")
    public ResponseEntity<?> getCartByUserId(@PathVariable("user_id") Long user_id){
        return null;
    }

    @PostMapping("/{cart_id}/{variantP_id}")
    public ResponseEntity<?> addNewProductToCart(@PathVariable("cart_id") Long cart_id,
                                                 @PathVariable("variantP_id") Long variantP_id,
                                                 @RequestBody() AddNewProduct addNewProduct,@RequestAttribute("current_user") CustomUserDetail user
    ){
        if(!Objects.equals(user.getUserId(), cart_id)){
            return new ResponseEntity<String>("Forbiden",null, 403);
        }
        Float res;
		try {
			if (addNewProduct.getQuantity() == null || addNewProduct.getQuantity() <= 0) {
	            throw new NotFoundException("Quantity must be a positive integer");
	        }
			res = cartLineItemService.addProductToCart(cart_id, variantP_id, addNewProduct);
			return new ResponseEntity<String>("Total price: " + res, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}  catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
 
}
