package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.services.interfaces.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/variant-products")
public class VariantProductController {
    @Autowired
    private VariantProductService variantProductService;
}
