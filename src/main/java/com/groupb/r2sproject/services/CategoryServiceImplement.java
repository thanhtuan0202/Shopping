package com.groupb.r2sproject.services;

import com.groupb.r2sproject.repositories.CategoryRepository;
import com.groupb.r2sproject.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImplement implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
}
