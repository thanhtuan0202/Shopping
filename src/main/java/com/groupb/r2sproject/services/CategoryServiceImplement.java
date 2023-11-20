package com.groupb.r2sproject.services;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.groupb.r2sproject.repositories.CategoryRepository;
import com.groupb.r2sproject.repositories.ProductRepository;
import com.groupb.r2sproject.services.interfaces.CategoryService;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.entities.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplement implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId, Integer pageSize, Integer pageNo, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        return productRepository.findByCategory_Id(categoryId, pageable);
    }
}

