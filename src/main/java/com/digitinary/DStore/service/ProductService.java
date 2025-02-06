package com.digitinary.DStore.service;

import com.digitinary.DStore.exception.ResourceNotFoundException;
import com.digitinary.DStore.model.entity.Category;
import com.digitinary.DStore.model.entity.Product;
import com.digitinary.DStore.model.mapper.CategoryMapper;
import com.digitinary.DStore.model.mapper.ProductMapper;
import com.digitinary.DStore.model.request.CreateCategoryRequest;
import com.digitinary.DStore.model.request.CreateProductRequest;
import com.digitinary.DStore.model.response.CategoryResponse;
import com.digitinary.DStore.model.response.ProductResponse;
import com.digitinary.DStore.repository.CategoryRepository;
import com.digitinary.DStore.repository.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public void createNewCategory(CreateCategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        categoryRepository.save(category);
        log.info("Category created successfully.");
    }

    public Set<CategoryResponse> getAllCategories() {
        Set<CategoryResponse> categories = categoryRepository.findAll()
                .stream().map(categoryMapper::toView).collect(Collectors.toSet());

        if (categories.isEmpty())
            throw new ResourceNotFoundException("No categories found.");

        return categories;
    }

    public CategoryResponse getCategoryById(Integer id) {
        return categoryRepository.findById(id).map(categoryMapper::toView)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    // FIXME: this method keeps running into an infinite loop (StackOverFlowException)
    @Transactional
    public void createNewProduct(Integer id, CreateProductRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        Product product = productMapper.toProduct(request);

        product.setCategory(category);

        productRepository.save(product);
        log.info("Product created successfully.");
    }

    public Set<ProductResponse> getAllProducts() {
        Set<ProductResponse> products = productRepository.findAll().stream().map(productMapper::toView).collect(Collectors.toSet());
        if (products.isEmpty())
            throw new ResourceNotFoundException("No products found.");
        return products;
    }

    public List<ProductResponse> searchProductsByName(String name){
        return productRepository.findByNameContainingIgnoreCase(name).stream()
                .map(productMapper::toView).collect(Collectors.toList());
    }

    public Page<ProductResponse> getProducts(String name, Integer categoryId, Double minPrice, Double maxPrice, Pageable pageable) {
        Specification<Product> spec = (root, query, cb) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (name != null) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (categoryId != null) {
                predicates.add(cb.equal(root.get("category").get("id"), categoryId));
            }
            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return productRepository.findAll(spec, pageable).map(productMapper::toView);
    }

}
