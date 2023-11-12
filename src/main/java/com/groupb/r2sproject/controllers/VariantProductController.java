package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.repositories.ProductRepository;
import com.groupb.r2sproject.services.interfaces.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/variant-products")
public class VariantProductController {
    @Autowired
    private VariantProductService variantProductService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/{product_id}")
    public ResponseEntity<VariantProductRespone> getProductVariants(@PathVariable Long product_id){
        Optional<Product> product = productRepository.findById(product_id);
        if (product.isPresent()) {
            Optional<VariantProductRespone> variantProductRespone = variantProductService.getVariantProducts(product_id);
            if (variantProductRespone.isPresent()) {
                return ResponseEntity.ok(variantProductRespone.get());
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
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
