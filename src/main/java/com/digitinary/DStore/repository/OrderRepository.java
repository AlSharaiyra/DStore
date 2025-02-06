package com.digitinary.DStore.repository;

import com.digitinary.DStore.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
