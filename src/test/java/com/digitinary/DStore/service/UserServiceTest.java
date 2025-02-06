package com.digitinary.DStore.service;

import com.digitinary.DStore.model.enums.Gender;
import com.digitinary.DStore.infra.exception.ResourceNotFoundException;
import com.digitinary.DStore.repository.entity.User;
import com.digitinary.DStore.model.mapper.UserMapper;
import com.digitinary.DStore.model.request.CreateUserRequest;
import com.digitinary.DStore.repository.repo.UserRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNewUser_EmailAlreadyExists() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("Walid", "Sharaiyra", "walidkhsharaiyra@gmail.com", "walid123456", 22, Gender.MALE);
        when(userRepository.findByEmailIgnoreCase(request.email())).thenReturn(Optional.of(new User()));

        // Act & Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> userService.createNewUser(request));
        assertEquals("Email address already exists", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testCreateNewUser_Success() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("Walid", "Sharaiyra", "walidkhsharaiyra@gmail.com", "walid123456", 22, Gender.MALE);
        when(userRepository.findByEmailIgnoreCase(request.email())).thenReturn(Optional.empty());
        User user = new User();
        when(userMapper.toUser(request)).thenReturn(user);

        // Act
        userService.createNewUser(request);

        // Assert
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById_UserNotFound() {
        // Arrange
        int userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId));
        assertEquals("User not found with id: " + userId, exception.getMessage());
    }

}
