package com.digitinary.DStore.repository.repo;

import com.digitinary.DStore.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
