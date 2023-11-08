package com.groupb.r2sproject.repositories;

import com.groupb.r2sproject.entities.VariantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarianProductRepository extends JpaRepository<VariantProduct,Long> {
}
