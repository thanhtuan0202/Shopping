package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;

import java.util.Optional;

public interface VariantProductService {
    Optional<VariantProductRespone> getVariantProducts(Long idProduct);
}
