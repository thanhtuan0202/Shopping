package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.OrderDTO.CreateNewOrder;
import com.groupb.r2sproject.dtos.OrderDTO.CreateOrderResponse;
import com.groupb.r2sproject.dtos.OrderDTO.CustomProductInfo;
import com.groupb.r2sproject.entities.Cart;
import com.groupb.r2sproject.entities.CartLineItem;
import com.groupb.r2sproject.entities.Order;
import com.groupb.r2sproject.repositories.CartLineItemRepository;
import com.groupb.r2sproject.repositories.OrderRepository;
import com.groupb.r2sproject.repositories.ProductRepository;
import com.groupb.r2sproject.services.interfaces.CartService;
import com.groupb.r2sproject.services.interfaces.OrderService;

import java.util.*;

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

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CreateOrderResponse createOrder(Long cart_id, CreateNewOrder order_info) {
        Cart cart = this.cartService.findById(cart_id);
        Set<CartLineItem> items = cart.getCartLineItems();
        Set<CartLineItem> order_item = new HashSet<>();
        Float total_price = 0f;
        for (CartLineItem item : items) {
            if (!item.getIs_Delete()) {
                order_item.add(item);
                total_price += item.getTotal_price();
            }
        }
        if(order_item.isEmpty()){
            return null;
        }
        Order order = new Order(order_info.getAddress(), order_info.getDelivery_time(), total_price, order_item);
        Order newOrder = this.orderRepository.save(order);
        for (CartLineItem item : order_item) {
            item.setOrder(newOrder);
            item.setIs_Delete(true);
            this.cartLineItemRepository.save(item);
        }
        return this.getOrder(order.getId(),cart_id);
    }

    @Override
    public Set<CreateOrderResponse> getAllOrder(Long user_id) {
        List<Order> orders = this.orderRepository.findAll();
        Set<CreateOrderResponse> res = new HashSet<>();
        for(Order od : orders) {
            Set<CustomProductInfo> cus = new HashSet<CustomProductInfo>();
            Long cart_id = Long.parseLong("0");
            for (CartLineItem item : od.getCartLineItems()){
                cart_id = item.getCart().getId();
                cus.add(new CustomProductInfo(
                        item.getVariant_product().getId(), item.getVariant_product().getProduct().getName(),
                        item.getVariant_product().getColor(),item.getVariant_product().getSize(),item.getVariant_product().getModel(),
                        item.getVariant_product().getPrice(),item.getQuantity(), item.getTotal_price()
                ));
            }
            if(Objects.equals(cart_id, user_id)) {
                res.add(new CreateOrderResponse(od.getId(), od.getAddress(), od.getDelivery_time(), od.getTotal_price(), cus));
            }
        }
        return res;
    }

    @Override
    public CreateOrderResponse getOrder(Long order_id, Long user_id) {
        Optional<Order> order = this.orderRepository.findById(order_id);
        if (order.isPresent()) {
            Order od =  order.get();
            Set<CustomProductInfo> cus = new HashSet<CustomProductInfo>();
            Long cart_id = Long.parseLong("0");
            for (CartLineItem item : od.getCartLineItems()){
                cart_id = item.getCart().getId();
                cus.add(new CustomProductInfo(
                        item.getVariant_product().getId(), item.getVariant_product().getProduct().getName(),
                        item.getVariant_product().getColor(),item.getVariant_product().getSize(),item.getVariant_product().getModel(),
                        item.getVariant_product().getPrice(),item.getQuantity(), item.getTotal_price()
                        ));
            }
            if(!Objects.equals(cart_id, user_id)){
                return null;
            }
            return new CreateOrderResponse(od.getId(),od.getAddress(),od.getDelivery_time(),od.getTotal_price(),cus);
        }
        return null;
    }
}
