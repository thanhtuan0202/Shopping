package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.entities.Category;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Product> getProductsByCategory(Long categoryId, Integer pageSize, Integer pageNo, String sortBy, String sortDir);
}
