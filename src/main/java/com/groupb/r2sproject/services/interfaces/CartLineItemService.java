package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.CartDTO.AddNewProduct;
import com.groupb.r2sproject.dtos.CartDTO.CartItemResponse;
import com.groupb.r2sproject.exceptions.NotFoundException;

import java.util.Set;

public interface CartLineItemService {
	Set<CartItemResponse> getItemByUserId(Long userId);
	Float addProductToCart(Long cart_id, Long variantP_id, AddNewProduct quantity) throws NumberFormatException, NotFoundException;
}
