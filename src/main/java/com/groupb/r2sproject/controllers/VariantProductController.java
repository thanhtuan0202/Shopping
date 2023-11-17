package com.groupb.r2sproject.controllers;

import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductUpdateDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
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
            Optional<VariantProductRespone> respone = variantProductService.getVariantProducts(product_id);
            if (respone.isPresent()) {
                return ResponseEntity.ok(respone.get());
            }
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/product/{product_id}")
    public ResponseEntity<VariantProductRespone> createNewVariantProduct(@PathVariable Long product_id, @RequestBody VariantProductDTO variantProductDTO){
    	VariantProductRespone res = variantProductService.createVariantProduct(product_id, variantProductDTO);
        if (res != null) {
            return new ResponseEntity<VariantProductRespone>(res, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<VariantProductRespone>((VariantProductRespone) null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{vp_id}")
    public ResponseEntity<VariantProductRespone> updateVariantProduct(@PathVariable("vp_id") Long vp_id, @RequestBody VariantProductUpdateDTO variantProductUpdateDTO){
    	VariantProductRespone res = variantProductService.updateVariantProduct(vp_id, variantProductUpdateDTO);
        if (res != null) {
            return new ResponseEntity<VariantProductRespone>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<VariantProductRespone>((VariantProductRespone) null, HttpStatus.NO_CONTENT);
        } 
    }
}
