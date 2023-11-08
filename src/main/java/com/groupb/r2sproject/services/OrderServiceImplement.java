package com.groupb.r2sproject.services;

import com.groupb.r2sproject.repositories.OrderRepository;
import com.groupb.r2sproject.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
}
