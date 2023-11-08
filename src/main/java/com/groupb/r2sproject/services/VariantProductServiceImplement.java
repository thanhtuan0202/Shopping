package com.groupb.r2sproject.services;

import com.groupb.r2sproject.repositories.VarianProductRepository;
import com.groupb.r2sproject.services.interfaces.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariantProductServiceImplement implements VariantProductService {
    @Autowired
    private VarianProductRepository varianProductRepository;

}
