package com.digitinary.DStore.controller;

import com.digitinary.DStore.exception.ResourceNotFoundException;
import com.digitinary.DStore.model.request.CreateCategoryRequest;
import com.digitinary.DStore.model.request.CreateProductRequest;
import com.digitinary.DStore.model.response.CategoryResponse;
import com.digitinary.DStore.model.response.ProductResponse;
import com.digitinary.DStore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Log4j2
@Tag(name = "Products",
        description = "A set of APIs to perform CRUD operations on products and their related data.")
@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @Operation(description = """
            An endpoint to add new categories.
            """)
    @PostMapping("/categories/add")
    public ResponseEntity<String> addCategory(@RequestBody @Valid CreateCategoryRequest request) {
        log.debug("Category to be added: {}", request);
        productService.createNewCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully.");
    }

    @Operation(description = """
            An endpoint to retrieve all categories.
            """)
    @GetMapping("/categories")
    public Set<CategoryResponse> getAllCategories() {
        return productService.getAllCategories();
    }

    @Operation(description = """
            An endpoint to get a category by id.
            """)
    @GetMapping("/categories/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return productService.getCategoryById(id);
    }


    @Operation(description = """
            An endpoint to add new products.
            """)
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestParam Integer categoryId, @RequestBody @Valid CreateProductRequest request) {
        log.debug("Product to be added: {}", request);
        productService.createNewProduct(categoryId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully.");
    }

    @Operation(description = """
            An endpoint to retrieve all products.
            """)
    @GetMapping("/products/all")
    public Set<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(description = """
            An endpoint to search for products by name, using request params.
            """)
    @GetMapping("/search")
    public List<ProductResponse> searchProductsByName(@RequestParam String name){
        List<ProductResponse> results = productService.searchProductsByName(name);
        if (results.isEmpty())
            throw new ResourceNotFoundException("No results found.");

        return results;
    }

    // FIXME
    @Operation(description = """
            An endpoint to retrieve all products, providing Pagination, Filtering, Sorting, and Searching.
            """)
    @GetMapping("/all")
    public Page<ProductResponse> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort) {

        Pageable pageable = getPageable(sort, page, size);
        return productService.getProducts(name, categoryId, minPrice, maxPrice, pageable);
    }

    private Pageable getPageable(String[] sort, int page, int size) {
        List<Order> orders = new ArrayList<>();
        for (String s : sort) {
            String[] _sort = s.split(",");
            orders.add(new Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
        }
        return PageRequest.of(page, size, Sort.by(orders));
    }

}
