package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.entities.Product;
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
    private ProductRepository  productRepository;
    
    @Autowired
    private CategoryRepository  categoryRepository;

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
	public ProductDetailDTO createProduct(ProductDetailDTO productDetailDTO) {
		Product newProduct = new Product();
		newProduct.setName(productDetailDTO.getName());
		Optional<Category> category = categoryRepository.findByName(productDetailDTO.getCategoryName());
		if (category.isPresent()) {
			newProduct.setCategory(category.get());
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Product product = productRepository.save(newProduct);
		return new ProductDetailDTO(product.getId(), product.getName(), product.getCategory().getName());
	}

	@Override
	public ProductDetailDTO updateProduct(Long id, ProductDetailDTO productDetailDTO) {
		Optional<Product> productOp = productRepository.findById(id);
		if (productOp.isPresent()) {
			productOp.get().setName(productDetailDTO.getName());
			Optional<Category> category = categoryRepository.findByName(productDetailDTO.getCategoryName());
			if (category.isPresent()) {
				productOp.get().setCategory(category.get());
				Product updateProduct = productRepository.save(productOp.get());
				return new ProductDetailDTO(updateProduct.getId() ,updateProduct.getName(), updateProduct.getCategory().getName());
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}	
		return null;
	}

	@Override
	public VariantProductRespone createVariantProduct(VariantProductDTO variantProductDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariantProductRespone updateVariantProduct(Long id, VariantProductDTO variantProductDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
