package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.CustomUserDetail;
import com.groupb.r2sproject.dtos.OrderDTO.CreateNewOrder;
import com.groupb.r2sproject.dtos.OrderDTO.CreateOrderResponse;
import com.groupb.r2sproject.entities.Order;
import com.groupb.r2sproject.services.CustomUserDetailServiceImplement;
import com.groupb.r2sproject.services.interfaces.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private final CustomUserDetailServiceImplement userDetailsService;

    public OrderController(CustomUserDetailServiceImplement userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @GetMapping()
    public ResponseEntity<?> getAllOrder(){
        Long user_id = this.userDetailsService.getCurrentUserDetails().getUserId();
        Set<CreateOrderResponse> res = this.orderService.getAllOrder(user_id);
        return new ResponseEntity<Set<CreateOrderResponse>>(res, HttpStatus.OK);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<?> getOrderById(@PathVariable("order_id") Long order_id){
        Long user_id = this.userDetailsService.getCurrentUserDetails().getUserId();
        CreateOrderResponse order = orderService.getOrder(order_id, user_id);
        if(order == null){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<CreateOrderResponse>(order, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createNewOrder(
        @RequestBody CreateNewOrder order_info
    ){
        Long user_id = this.userDetailsService.getCurrentUserDetails().getUserId();
        CreateOrderResponse order = this.orderService.createOrder(user_id, order_info);
        if (order == null){
            return ResponseEntity.noContent().build();
        }
        //find cart -> take list of products in cart -> create order -> update each product with isDeleted = true and add order id to this product
        return new ResponseEntity<CreateOrderResponse>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> changeOrderDetails(@RequestBody CreateNewOrder order_info, @PathVariable("orderId") Long order_id){
        return null;
    }
}
