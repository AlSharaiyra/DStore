package com.digitinary.DStore.repository.repo;

import com.digitinary.DStore.repository.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
