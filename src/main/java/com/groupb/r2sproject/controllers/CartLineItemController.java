package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.services.interfaces.CartLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart-lines")
public class CartLineItemController {
    @Autowired
    private CartLineItemService cartLineItemService;
}
