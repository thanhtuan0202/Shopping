package com.groupb.r2sproject.services.interfaces;


import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;

public interface ProductService {
    ProductDetailDTO getProductDetailById(Long id);
}
