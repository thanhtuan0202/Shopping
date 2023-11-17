package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductUpdateDTO;
import com.groupb.r2sproject.exceptions.NotFoundException;

import java.util.Optional;

public interface VariantProductService {
    Optional<VariantProductRespone> getVariantProducts(Long idProduct);
    VariantProductRespone createVariantProduct(Long product_id, VariantProductDTO variantProductDTO) throws NotFoundException;
	VariantProductRespone updateVariantProduct(Long id, VariantProductUpdateDTO variantProductUpdateDTO) throws NotFoundException;
}
