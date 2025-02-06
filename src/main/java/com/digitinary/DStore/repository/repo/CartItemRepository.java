package com.digitinary.DStore.repository.repo;

import com.digitinary.DStore.repository.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
