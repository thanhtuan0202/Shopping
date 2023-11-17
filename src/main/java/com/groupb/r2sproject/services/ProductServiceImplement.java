package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.exceptions.NotFoundException;
import com.groupb.r2sproject.entities.Category;
import com.groupb.r2sproject.repositories.CategoryRepository;
import com.groupb.r2sproject.repositories.ProductRepository;
import com.groupb.r2sproject.services.interfaces.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

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

	@Override
	public ProductDetailDTO createProduct(ProductDetailDTO productDetailDTO) throws NotFoundException {
		Product newProduct = new Product();
		newProduct.setName(productDetailDTO.getName());
//		Check if categoryName exists
		Category category = categoryRepository.findByName(productDetailDTO.getCategoryName())
				.orElseThrow(() -> new NotFoundException("Category not found"));
		
		newProduct.setCategory(category);
		Product product = productRepository.save(newProduct);
		return new ProductDetailDTO(product.getId(), product.getName(), product.getCategory().getName());
	}

	@Override
	public ProductDetailDTO updateProduct(Long id, ProductDetailDTO productDetailDTO) throws NotFoundException {
//		Check if productId and categoryName exist
		Product productOp = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Product not found"));
		Category category = categoryRepository.findByName(productDetailDTO.getCategoryName())
				.orElseThrow(() -> new NotFoundException("Category not found"));
		
		productOp.setName(productDetailDTO.getName());
		productOp.setCategory(category);
		Product updateProduct = productRepository.save(productOp);
		return new ProductDetailDTO(updateProduct.getId(), updateProduct.getName(),
				updateProduct.getCategory().getName());

	}
}
