package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.CartDTO.AddNewProduct;
import com.groupb.r2sproject.exceptions.NotFoundException;

public interface CartLineItemService {
	Float addProductToCart(Long cart_id, Long variantP_id, AddNewProduct quantity) throws NumberFormatException, NotFoundException;
}
