package com.digitinary.DStore.repository;

import com.digitinary.DStore.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
