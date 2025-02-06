package com.digitinary.DStore.repository.repo;

import com.digitinary.DStore.repository.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
