package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.OrderDTO.CreateNewOrder;
import com.groupb.r2sproject.dtos.OrderDTO.CreateOrderResponse;
import com.groupb.r2sproject.entities.Order;

import java.util.Set;

public interface OrderService {
    public CreateOrderResponse createOrder(Long cart_id, CreateNewOrder order_info);
    public Set<CreateOrderResponse> getAllOrder(Long user_id);
    public CreateOrderResponse getOrder(Long order_id, Long user_id);
}
