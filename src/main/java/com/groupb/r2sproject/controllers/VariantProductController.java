package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.services.interfaces.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/variant-products")
public class VariantProductController {
    @Autowired
    private VariantProductService variantProductService;

    @GetMapping("/product/{product_id}")
    public ResponseEntity<?> getVariantProductByProduct(@PathVariable("product_id") Long product_id){
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createNewVariantProduct(){
        return null;
    }

    @PutMapping("/{vp_id}")
    public ResponseEntity<?> updateVariantProduct(@PathVariable("vp_id") Long vp_id){
        return null;
    }
}
