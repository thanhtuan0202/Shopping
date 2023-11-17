package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductUpdateDTO;
import com.groupb.r2sproject.entities.Category;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.entities.VariantProduct;
import com.groupb.r2sproject.exceptions.NotFoundException;
import com.groupb.r2sproject.repositories.ProductRepository;
import com.groupb.r2sproject.repositories.VarianProductRepository;
import com.groupb.r2sproject.services.interfaces.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VariantProductServiceImplement implements VariantProductService {
	@Autowired
	private VarianProductRepository varianProductRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Optional<VariantProductRespone> getVariantProducts(Long idProduct) {
		Optional<Product> productOpt = productRepository.findById(idProduct);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			VariantProductRespone variantProductRespone = new VariantProductRespone();
			variantProductRespone.setId_product(product.getId());
			variantProductRespone.setName(product.getName());

			List<VariantProductDTO> variantProductDTOS = product.getVariantProducts().stream().map(variantProduct -> {
				VariantProductDTO dto = new VariantProductDTO();
				dto.setId(variantProduct.getId());
				dto.setColor(variantProduct.getColor());
				dto.setSize(variantProduct.getSize());
				dto.setPrice(variantProduct.getPrice());
				return dto;
			}).collect(Collectors.toList());
			variantProductRespone.setVariantProducts(variantProductDTOS);
			return Optional.of(variantProductRespone);
		}
		return Optional.empty();
	}

	@Override
	public VariantProductRespone createVariantProduct(Long product_id, VariantProductDTO variantProductDTO)
			throws NotFoundException {
		// Check if product existed
		Product product = productRepository.findById(product_id)
				.orElseThrow(() -> new NotFoundException("Product not found"));

		VariantProduct newVarProduct = new VariantProduct();
		newVarProduct.setColor(variantProductDTO.getColor());
		newVarProduct.setSize(variantProductDTO.getSize());
		newVarProduct.setModel(variantProductDTO.getModel());
		// round in 2 decimal
		Float price = Float.parseFloat(String.format("%.2f", variantProductDTO.getPrice()));
		newVarProduct.setPrice(price);
		// link to Product entity
		newVarProduct.setProduct(product);
		VariantProduct variantProduct = varianProductRepository.save(newVarProduct);

//			Set<VariantProduct> varProducts = new HashSet<VariantProduct>();
//			varProducts.add(newVarProduct);
//			//link to Variant product entity
//			product.get().setVariantProducts(varProducts); //required Set

		variantProductDTO.setId(variantProduct.getId());
		List<VariantProductDTO> varProductsDTO = new ArrayList<VariantProductDTO>();
		varProductsDTO.add(variantProductDTO);
		return new VariantProductRespone(variantProduct.getProduct().getId(), variantProduct.getProduct().getName(),
				varProductsDTO // required List
		);
	}

	@Override
	public VariantProductRespone updateVariantProduct(Long id, VariantProductUpdateDTO variantProductUpdateDTO)
			throws NotFoundException {
		// check if variant product existed
		VariantProduct varProductOp = varianProductRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Variant product not found"));

//			set values for variant product
		varProductOp.setColor(variantProductUpdateDTO.getColor());
		varProductOp.setSize(variantProductUpdateDTO.getSize());
		varProductOp.setModel(variantProductUpdateDTO.getModel());
		// round in 2 decimal
		Float price = Float.parseFloat(String.format("%.2f", variantProductUpdateDTO.getPrice()));
		varProductOp.setPrice(price);

		// check if product existed
		Product product = productRepository.findById(variantProductUpdateDTO.getProduct_id())
				.orElseThrow(() -> new NotFoundException("Product not found"));

		varProductOp.setProduct(product);
		VariantProduct updateVarProduct = varianProductRepository.save(varProductOp);

		VariantProductDTO variantProductDTO = new VariantProductDTO();
		variantProductDTO.setId(id);
		variantProductDTO.setColor(variantProductUpdateDTO.getColor());
		variantProductDTO.setSize(variantProductUpdateDTO.getSize());
		variantProductDTO.setModel(variantProductUpdateDTO.getModel());
		variantProductDTO.setPrice(variantProductUpdateDTO.getPrice());

		List<VariantProductDTO> varProductsDTO = new ArrayList<VariantProductDTO>();
		varProductsDTO.add(variantProductDTO);

		return new VariantProductRespone(product.getId(), product.getName(), varProductsDTO);
	}
}
