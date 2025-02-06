package com.digitinary.DStore.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String description;

    @Basic(optional = false)
    private Double price;

    @Basic(optional = false)
    @Column(name = "in_stock")
    private Integer inStock;

    @Column(name = "last_modified")
    private Timestamp lastModified;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // TODO: When a product is deleted, deleted the associated records in the cart_items table.
}
