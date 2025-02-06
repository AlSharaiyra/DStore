package com.digitinary.DStore.repository;

import com.digitinary.DStore.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
