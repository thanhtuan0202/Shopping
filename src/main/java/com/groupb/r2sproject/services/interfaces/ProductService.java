package com.groupb.r2sproject.services.interfaces;


import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;

public interface ProductService {
	ProductDetailDTO getProductDetailById(Long id);
	ProductDetailDTO createProduct(ProductDetailDTO productDetailDTO);
	ProductDetailDTO updateProduct(Long id, ProductDetailDTO productDetailDTO);
}
