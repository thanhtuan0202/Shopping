package com.groupb.r2sproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.groupb.r2sproject.dtos.ApiResponse;
import com.groupb.r2sproject.dtos.ProductDTO.ProductByCategory;
import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.exceptions.NotFoundException;
import com.groupb.r2sproject.services.interfaces.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
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
    public ResponseEntity<?> getProductByCategory(@PathVariable("category") Long category_id,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                  @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                                  @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                  @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) throws JsonProcessingException{
        ProductByCategory res = this.productService.getProductByCategory(category_id, pageSize, pageNo, sortBy,sortDir);
        if(res == null){
            return ResponseEntity.noContent().build();
        }
        else{
            ApiResponse<ProductByCategory> response = new ApiResponse<>(res);
            return ResponseEntity.ok(response);
        }
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
    public ResponseEntity<?> createNewProduct(@RequestBody ProductDetailDTO productDetailDTO){
    	ProductDetailDTO res;
		try {
			res = productService.createProduct(productDetailDTO);
			return new ResponseEntity<ProductDetailDTO>(res, HttpStatus.CREATED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDetailDTO productDetailDTO, @PathVariable("product_id" )Long product_id){
    	ProductDetailDTO res;
		try {
			res = productService.updateProduct(product_id, productDetailDTO);
			return new ResponseEntity<ProductDetailDTO>(res, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
}
