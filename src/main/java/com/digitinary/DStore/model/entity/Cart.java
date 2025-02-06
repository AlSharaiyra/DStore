package com.digitinary.DStore.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_of_items")
    private Integer numberOfItems;

    // If cart is deleted, the cart items are also deleted.
    // orphanRemoval=true to remove the cart item from the database once it is removed from the collection
    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    // @JsonIgnore
    // @OneToOne(mappedBy = "cart")
    // private User user;

}
