package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.OrderDTO.CreateNewOrder;
import com.groupb.r2sproject.entities.Cart;
import com.groupb.r2sproject.entities.CartLineItem;
import com.groupb.r2sproject.entities.Order;
import com.groupb.r2sproject.repositories.CartLineItemRepository;
import com.groupb.r2sproject.repositories.OrderRepository;
import com.groupb.r2sproject.services.interfaces.CartService;
import com.groupb.r2sproject.services.interfaces.OrderService;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired 
    private CartLineItemRepository cartLineItemRepository;
    @Override
    public void createOrder(Long cart_id, CreateNewOrder order_info) {
        Cart cart = this.cartService.findById(cart_id);
        Set<CartLineItem> items = cart.getCartLineItems();
        Float total_price = 0f;
        for(CartLineItem item: items) {
            total_price += item.getTotal_price();
        }
        Order order = new Order(order_info.getAddress(),order_info.getDelivery_time(),total_price,items);
        Order newOrder = this.orderRepository.save(order);
        for(CartLineItem item: items) {
            item.setOrder(newOrder);
            item.set_delete(true);
            this.cartLineItemRepository.save(item);
            
        }

    }
}
