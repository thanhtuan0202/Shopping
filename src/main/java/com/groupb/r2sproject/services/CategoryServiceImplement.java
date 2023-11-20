package com.groupb.r2sproject.services;
import com.groupb.r2sproject.dtos.CategoryDTO.CategoryResponse;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImplement implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Set<CategoryResponse> getAllCategories() {
        List<Category> cate_lst = categoryRepository.findAll();
        Set<CategoryResponse> res = new HashSet<>();
        for(Category item: cate_lst) {
            res.add(new CategoryResponse(item.getId(), item.getName(), item.getDescription()));
        }
        return res;
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId, Integer pageSize, Integer pageNo, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        return productRepository.findByCategory_Id(categoryId, pageable);
    }
}

