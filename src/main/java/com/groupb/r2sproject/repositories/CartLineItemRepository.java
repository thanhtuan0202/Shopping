package com.groupb.r2sproject.repositories;

import com.groupb.r2sproject.entities.Cart;
import com.groupb.r2sproject.entities.CartLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartLineItemRepository extends JpaRepository<CartLineItem,Long> {
    Optional<CartLineItem> findByCart(Cart cart);
    Optional<CartLineItem> findAllByCart(Cart cart);
}
