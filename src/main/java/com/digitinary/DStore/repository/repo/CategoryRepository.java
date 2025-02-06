package com.digitinary.DStore.repository.repo;

import com.digitinary.DStore.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
