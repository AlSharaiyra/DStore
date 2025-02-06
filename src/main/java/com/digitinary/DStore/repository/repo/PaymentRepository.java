package com.digitinary.DStore.repository.repo;

import com.digitinary.DStore.repository.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
