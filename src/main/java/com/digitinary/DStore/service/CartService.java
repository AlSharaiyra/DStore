package com.digitinary.DStore.service;

import com.digitinary.DStore.infra.exception.ResourceNotFoundException;
import com.digitinary.DStore.repository.entity.Cart;
import com.digitinary.DStore.repository.entity.CartItem;
import com.digitinary.DStore.repository.entity.Product;
import com.digitinary.DStore.model.request.CartItemRequest;
import com.digitinary.DStore.repository.repo.CartRepository;
import com.digitinary.DStore.repository.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addProductToCart(Integer cart_id, Integer product_id, CartItemRequest request) {
        Cart cart = cartRepository.findById(cart_id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cart_id));

        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + product_id));

        if (product.getInStock() < request.quantity()) {
            // TODO handle exception
            throw new IllegalArgumentException("Not enough stock available for product: " + product.getName());
        }

        // Check if the product is already in the cart
        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(product_id))
                .findFirst();

        if (existingCartItem.isPresent()) {
            // If the product already exists, update the quantity
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.quantity());
        } else {
            // Otherwise, create a new cart item
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(request.quantity());
            cart.getCartItems().add(newCartItem);
        }
        cartRepository.save(cart);

    }
}
