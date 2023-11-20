package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.CategoryDTO.CategoryResponse;
import com.groupb.r2sproject.entities.Category;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.dtos.ProductDTO.ProductDetailDTO;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Set<CategoryResponse> getAllCategories();

    List<Product> getProductsByCategory(Long categoryId, Integer pageSize, Integer pageNo, String sortBy, String sortDir);
}
