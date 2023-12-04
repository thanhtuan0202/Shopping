package com.groupb.r2sproject.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupb.r2sproject.dtos.ProductDTO.ProductByCategory;
import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.dtos.ProductDTO.ProductResponse;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.exceptions.NotFoundException;
import com.groupb.r2sproject.entities.Category;
import com.groupb.r2sproject.repositories.CategoryRepository;
import com.groupb.r2sproject.repositories.ProductRepository;
import com.groupb.r2sproject.services.interfaces.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImplement implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	ObjectMapper mapper = new ObjectMapper();
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

	@Override
	public ProductByCategory getProductByCategory(Long category_id, Integer pageSize, Integer pageNo, String sortBy, String sortDir) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
		List<Product> products = productRepository.findByCategory_Id(category_id, pageable);
		if(products.isEmpty()){
			return null;
		}
		else{
			ProductByCategory res = new ProductByCategory();
			res.setCategory_id(category_id);
			Set<ProductResponse> product_lst = new HashSet<>();
			for(Product item : products){
				res.setCategory_name(item.getCategory().getName());
				product_lst.add(new ProductResponse(item.getId(), item.getName()));
			}
			res.setProducts(product_lst);
			return res;
		}

	}

}
