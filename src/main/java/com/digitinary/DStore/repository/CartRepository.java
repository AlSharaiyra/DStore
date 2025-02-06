package com.digitinary.DStore.repository;

import com.digitinary.DStore.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
