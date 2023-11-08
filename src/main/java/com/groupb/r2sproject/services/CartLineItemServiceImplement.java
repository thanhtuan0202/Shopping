package com.groupb.r2sproject.services;

import com.groupb.r2sproject.repositories.CartLineItemRepository;
import com.groupb.r2sproject.services.interfaces.CartLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartLineItemServiceImplement implements CartLineItemService {
    @Autowired
    private CartLineItemRepository cartLineItemRepository;

}
