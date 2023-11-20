package com.groupb.r2sproject.repositories;

import org.springframework.data.domain.Pageable;
import com.groupb.r2sproject.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_Id(Long categoryId, Pageable pageable);
}

