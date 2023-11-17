package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.CartDTO.AddNewProduct;
import com.groupb.r2sproject.entities.Cart;
import com.groupb.r2sproject.entities.CartLineItem;
import com.groupb.r2sproject.entities.VariantProduct;
import com.groupb.r2sproject.exceptions.NotFoundException;
import com.groupb.r2sproject.repositories.CartLineItemRepository;
import com.groupb.r2sproject.repositories.CartRepository;
import com.groupb.r2sproject.repositories.VarianProductRepository;
import com.groupb.r2sproject.services.interfaces.CartLineItemService;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartLineItemServiceImplement implements CartLineItemService {
	@Autowired
	private CartLineItemRepository cartLineItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private VarianProductRepository varianProductRepository;

	@Override
	public Float addProductToCart(Long cart_id, Long variantP_id, AddNewProduct addNewProduct) throws NotFoundException {
//        	check if cart_id, variantP_id exist
		Cart cart = cartRepository.findById(cart_id).orElseThrow(() -> new NotFoundException("Cart not found"));
		VariantProduct vp = varianProductRepository.findById(variantP_id)
				.orElseThrow(() -> new NotFoundException("Variant Product not found"));

		CartLineItem cartLineItem = new CartLineItem();
		cartLineItem.setCreate_at(ZonedDateTime.now());
		cartLineItem.set_delete(false);

		cartLineItem.setQuantity(addNewProduct.getQuantity());
		Float totalPrice = vp.getPrice() * addNewProduct.getQuantity();
		//round in 2 decimal
		totalPrice = Float.parseFloat(String.format("%.2f", totalPrice));
		
		cartLineItem.setTotal_price(totalPrice);
		cartLineItem.setCart(cart);
		cartLineItem.setVariant_product(vp);

		CartLineItem addToCart = cartLineItemRepository.save(cartLineItem);
		return addToCart.getTotal_price();
	}
}
