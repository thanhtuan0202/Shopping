package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.CustomUserDetail;
import com.groupb.r2sproject.dtos.OrderDTO.CreateNewOrder;
import com.groupb.r2sproject.dtos.OrderDTO.CreateOrderResponse;
import com.groupb.r2sproject.entities.Order;
import com.groupb.r2sproject.services.interfaces.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @GetMapping()
    public ResponseEntity<?> getAllOrder(){
        return null;
    }

    @GetMapping("/{user_id}/{order_id}")
    public ResponseEntity<?> getOrderById(@PathVariable("user_id") Long user_id,@PathVariable("order_id") Long order_id,@RequestAttribute("current_user") CustomUserDetail user){
        if(!Objects.equals(user.getUserId(), user_id)){
            return new ResponseEntity<String>("Forbiden",null, 403);
        }
        CreateOrderResponse order = orderService.getOrder(order_id);
        if(order == null){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<CreateOrderResponse>(order, HttpStatus.OK);
    }

    @PostMapping("/{user_id}/{cart_id}")
    public ResponseEntity<?> createNewOrder(
        @PathVariable("user_id") Long user_id,@PathVariable("cart_id") Long cart_id, @RequestBody CreateNewOrder order_info,
        @RequestAttribute("current_user") CustomUserDetail user
    ){
        if(!Objects.equals(user.getUserId(), user_id)){
            return new ResponseEntity<String>("Forbiden",null, 403);
        }
        CreateOrderResponse order = this.orderService.createOrder(cart_id, order_info);
        if (order == null){
            return ResponseEntity.noContent().build();
        }
        //find cart -> take list of products in cart -> create order -> update each product with isDeleted = true and add order id to this product
        return new ResponseEntity<CreateOrderResponse>(order, HttpStatus.OK);
    }
}
