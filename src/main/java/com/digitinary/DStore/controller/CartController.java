package com.digitinary.DStore.controller;

import com.digitinary.DStore.model.request.CartItemRequest;
import com.digitinary.DStore.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Tag(name = "Carts",
        description = "A set of APIs to manage and handle users` carts.")
@RequiredArgsConstructor
@RestController
@RequestMapping("carts")
public class CartController {
    private final CartService cartService;

    @Operation(description = """
            An endpoint to add products to carts.
            """)
    @PostMapping("/{cart_id}/addToCart/{product_id}")
    public ResponseEntity<String> addToCart(@PathVariable Integer cart_id, @PathVariable Integer product_id,
                                            @RequestBody @Valid CartItemRequest request){
        log.debug("Adding product with id: {} to cart with id: {}", product_id, cart_id);
        cartService.addProductToCart(cart_id, product_id, request);
        return ResponseEntity.ok("Product added to cart successfully.");
    }

}
