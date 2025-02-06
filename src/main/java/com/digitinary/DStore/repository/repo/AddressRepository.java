package com.digitinary.DStore.repository.repo;

import com.digitinary.DStore.repository.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
