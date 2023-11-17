package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.OrderDTO.CreateNewOrder;
import com.groupb.r2sproject.dtos.OrderDTO.CreateOrderResponse;
import com.groupb.r2sproject.entities.Order;

public interface OrderService {
    public CreateOrderResponse createOrder(Long cart_id, CreateNewOrder order_info);
    public void getAllOrder();
    public CreateOrderResponse getOrder(Long order_id);
}
