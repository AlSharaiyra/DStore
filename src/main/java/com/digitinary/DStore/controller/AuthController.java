package com.digitinary.DStore.controller;

import com.digitinary.DStore.model.request.CreateUserRequest;
import com.digitinary.DStore.model.request.UpdateUserRequest;
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
@Tag(name = "Auth",
        description = "A set of APIs to manage authentication and users.")
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class AuthController {
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
}
