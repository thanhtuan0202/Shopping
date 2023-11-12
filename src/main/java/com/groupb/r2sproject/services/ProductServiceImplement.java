package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.repositories.ProductRepository;
import com.groupb.r2sproject.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductRepository  productRepository;

    @Override
    public ProductDetailDTO getProductDetailById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductDetailDTO productDetailDTO = new ProductDetailDTO();
            productDetailDTO.setId(product.get().getId());
            productDetailDTO.setName(product.get().getName());
            productDetailDTO.setCategoryName(product.get().getCategory().getName());
            return productDetailDTO;
        }
        return null;
    }
}
