package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.OrderDTO.CreateNewOrder;

public interface OrderService {
    public void createOrder(Long cart_id, CreateNewOrder order_info);
}
