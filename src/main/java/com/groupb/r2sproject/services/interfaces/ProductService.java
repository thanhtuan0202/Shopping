package com.groupb.r2sproject.services.interfaces;


import com.groupb.r2sproject.dtos.ProductDTO.ProductByCategory;
import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.exceptions.NotFoundException;

public interface ProductService {
	ProductDetailDTO getProductDetailById(Long id);
	ProductDetailDTO createProduct(ProductDetailDTO productDetailDTO) throws NotFoundException;
	ProductDetailDTO updateProduct(Long id, ProductDetailDTO productDetailDTO) throws NotFoundException;
	ProductByCategory getProductByCategory(Long category_id,Integer pageSize, Integer pageNo, String sortBy, String sortDir);
}
