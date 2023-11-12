package com.groupb.r2sproject.services;

import com.groupb.r2sproject.repositories.CartRepository;
import com.groupb.r2sproject.entities.Cart;
import com.groupb.r2sproject.services.interfaces.CartService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplement implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findById(Long cart_id) {
        Optional<Cart> cart = cartRepository.findById(cart_id);
        return cart.orElse(null);
    }
}
