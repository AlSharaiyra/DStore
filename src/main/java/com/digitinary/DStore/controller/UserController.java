package com.digitinary.DStore.controller;

import com.digitinary.DStore.model.request.CreateOrUpdateAddressRequest;
import com.digitinary.DStore.model.request.CreateUserRequest;
import com.digitinary.DStore.model.request.UpdateUserRequest;
import com.digitinary.DStore.model.response.AddressResponse;
import com.digitinary.DStore.model.response.UserResponse;
import com.digitinary.DStore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Log4j2
@Tag(name = "Users",
        description = "A set of APIs to perform CRUD operations on users and their related data.")
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Operation(description = """
            An endpoint to register new users.
            """)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid CreateUserRequest request) {
        log.debug("User to be created: {}", request);
        userService.createNewUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
    }

    @Operation(description = """
            An endpoint to retrieve all users.
            """)
    @GetMapping
    public Set<UserResponse> getAllUsers() {
        log.info("Returning all users.");
        return userService.getAllUsers();
    }

    @Operation(description = """
            An endpoint to get a user by their id.
            """)
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @Operation(description = """
            An endpoint to update an existing user.
            """)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody @Valid UpdateUserRequest request) {
        log.debug("User update request: {}", request);
        userService.updateUser(id, request);
        return ResponseEntity.ok("User updated successfully.");
    }

    @Operation(description = """
            An endpoint to delete an existing user.
            """)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        log.warn("User to be deleted: {}", userService.getUserById(id));
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @Operation(description = """
            An endpoint to assign a new address to an existing user.
            """)
    @PostMapping("/{id}/addresses/add")
    public ResponseEntity<String> addAndAssignAddress(@PathVariable Integer id, @RequestBody @Valid CreateOrUpdateAddressRequest request) {
        log.debug("Address to be created: {}", request);
        userService.addAndAssignAddress(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Address created and assigned to user successfully.");
    }

    @Operation(description = """
            An endpoint to retrieve the addresses of an existing user.
            """)
    @GetMapping("/{id}/addresses")
    public Set<AddressResponse> getUserAddresses(@PathVariable Integer id) {
        log.info("Returning all addresses of user with id: {}", id);
        return userService.getUserAddresses(id);
    }

    @Operation(description = """
            An endpoint to retrieve a specific address of an existing user.
            """)
    @GetMapping("/{user_id}/addresses/{address_id}")
    public AddressResponse getSingleUserAddress(@PathVariable Integer user_id, @PathVariable Integer address_id) {
        log.info("Returning all addresses of user with id: {}", user_id);
        return userService.getSingleUserAddress(user_id, address_id);
    }

    @Operation(description = """
            An endpoint to update a specific address of an existing user.
            """)
    @PutMapping("/{user_id}/addresses/{address_id}")
    public ResponseEntity<String> updateAddress(
            @PathVariable Integer user_id,
            @PathVariable Integer address_id,
            @RequestBody CreateOrUpdateAddressRequest request
    ) {
        log.debug("Address update request: {}", request);
        userService.updateAddress(user_id, address_id, request);
        return ResponseEntity.ok("Address updated successfully.");
    }

    @Operation(description = """
            An endpoint to delete a specific address of an existing user.
            """)
    @DeleteMapping("/{user_id}/addresses/{address_id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer user_id, @PathVariable Integer address_id){
        log.warn("Deleting address with id: {}", address_id);
        userService.deleteAddress(user_id, address_id);
        return ResponseEntity.ok("Address deleted successfully.");
    }
}
