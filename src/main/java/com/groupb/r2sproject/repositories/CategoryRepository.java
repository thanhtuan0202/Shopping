package com.groupb.r2sproject.repositories;

import com.groupb.r2sproject.entities.Category;
import com.groupb.r2sproject.entities.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}

