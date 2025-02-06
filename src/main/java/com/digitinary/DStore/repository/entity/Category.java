package com.digitinary.DStore.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String name;

    //    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    // Proper helper method to avoid infinite loops
    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this); // Maintain bidirectional consistency
    }
}
