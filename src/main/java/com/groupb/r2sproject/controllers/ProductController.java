package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        return null;
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductByCategory(@PathVariable("category") Long category_id){
        return null;
    }
    
    @GetMapping("/{product_id}")
    public ResponseEntity<ProductDetailDTO> getProductById(@PathVariable("product_id") Long product_id){
        ProductDetailDTO productDetailDTO = productService.getProductDetailById(product_id);
        if (productDetailDTO != null) {
            return ResponseEntity.ok(productDetailDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(){
        return null;
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<?> updateProduct(@PathVariable("product_id") Long product_id){
        return null;
    }
}
