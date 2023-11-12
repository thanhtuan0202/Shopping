package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.OrderDTO.CreateNewOrder;
import com.groupb.r2sproject.services.interfaces.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @GetMapping()
    public ResponseEntity<?> getAllOrder(){
        return null;
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<?> getOrderById(@PathVariable("order_id") Long order_id){
        return null;
    }

    @PostMapping("/{cart_id}")
    public ResponseEntity<?> createNewOrder(@PathVariable("cart_id") Long cart_id, @RequestBody CreateNewOrder order_info){
        this.orderService.createOrder(cart_id, order_info);
        //find cart -> take list of products in cart -> create order -> update each product with isDeleted = true and add order id to this product
        return null;
    }
}
