package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductDTO;
import com.groupb.r2sproject.dtos.VariantProductDTO.VariantProductRespone;
import com.groupb.r2sproject.entities.Product;
import com.groupb.r2sproject.repositories.ProductRepository;
import com.groupb.r2sproject.repositories.VarianProductRepository;
import com.groupb.r2sproject.services.interfaces.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                dto.setPrice(Double.valueOf(variantProduct.getPrice()));
                return dto;
            }).collect(Collectors.toList());
            variantProductRespone.setVariantProducts(variantProductDTOS);
            return Optional.of(variantProductRespone);
        }
        return Optional.empty();
    }
}
