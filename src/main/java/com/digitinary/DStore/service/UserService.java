package com.digitinary.DStore.service;

import com.digitinary.DStore.infra.exception.ResourceNotFoundException;
import com.digitinary.DStore.repository.entity.Address;
import com.digitinary.DStore.repository.entity.Cart;
import com.digitinary.DStore.repository.entity.User;
import com.digitinary.DStore.model.mapper.AddressMapper;
import com.digitinary.DStore.model.mapper.UserMapper;
import com.digitinary.DStore.model.request.CreateOrUpdateAddressRequest;
import com.digitinary.DStore.model.request.CreateUserRequest;
import com.digitinary.DStore.model.request.UpdateUserRequest;
import com.digitinary.DStore.model.response.AddressResponse;
import com.digitinary.DStore.model.response.UserResponse;
import com.digitinary.DStore.repository.repo.AddressRepository;
import com.digitinary.DStore.repository.repo.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Transactional
    public void createNewUser(CreateUserRequest request) {
        if (userRepository.findByEmailIgnoreCase(request.email()).isPresent()) {
            log.error("Failed to create user, email address already exists.");
            throw new ValidationException("Email address already exists");
        }
        User user = userMapper.toUser(request);

        Cart userCart = new Cart();
        userCart.setNumberOfItems(0);

        user.setCart(userCart);
//        userCart.setUser(user);

        userRepository.save(user);
        log.info("User created successfully.");
    }

    public Set<UserResponse> getAllUsers() {
        Set<UserResponse> users = userRepository.findAll().stream().map(userMapper::toView).collect(Collectors.toSet());
        if (users.isEmpty())
            throw new ResourceNotFoundException("No users found.");
        return users;
    }

    public UserResponse getUserById(Integer id) {
        return userRepository.findById(id).map(userMapper::toView)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public void updateUser(Integer id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        Optional<User> tmpUserToCheckEmail = userRepository.findByEmailIgnoreCase(request.email());
        if (tmpUserToCheckEmail.isPresent() && !user.getId().equals(tmpUserToCheckEmail.get().getId())) {
            throw new ValidationException("Email address already exists");
        }

        if (request.firstName() != null) user.setFirstName(request.firstName());
        if (request.lastName() != null) user.setLastName(request.lastName());
        if (request.email() != null) user.setEmail(request.email());
        if (request.age() != null) user.setAge(request.age());
        if (request.gender() != null) user.setGender(request.gender());

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
        log.info("User deleted successfully.");
    }

    // NOTE: Fixed
    @Transactional
    public void addAndAssignAddress(Integer id, CreateOrUpdateAddressRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        Address address = addressMapper.toAddress(request);

        // Use helper method to avoid infinite loop
        address.setUser(user);

        addressRepository.save(address); // Saving user saves addresses due to CascadeType.ALL

        log.info("Address created successfully.");
    }

    // FIXME
    @Transactional
    public Set<AddressResponse> getUserAddresses(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        if (user.getAddresses().isEmpty())
            throw new ResourceNotFoundException("No addresses found");

        return user.getAddresses().stream().map(addressMapper::toView).collect(Collectors.toSet());
    }

    // FIXME
    @Transactional
    private Address getUserAddress(Integer user_id, Integer address_id) {
//        Set<Address> addresses = userRepository.findById(user_id).map(User::getAddresses)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));
//
//        if (addresses.isEmpty())
//            throw new ResourceNotFoundException("Address not found with id: " + address_id);
//
//        return addresses.stream().filter(address -> address.getId().equals(address_id)).findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + address_id));

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user_id));

        Set<Address> addresses = user.getAddresses();

        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("No addresses found for user with id: " + user_id);
        }

        return addresses.stream()
                .filter(address -> Objects.equals(address.getId(), address_id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Address with id " + address_id + " not found for user " + user_id));
    }

    @Transactional
    public AddressResponse getSingleUserAddress(Integer user_id, Integer address_id) {
        return addressMapper.toView(getUserAddress(user_id, address_id));
    }

    @Transactional
    public void updateAddress(Integer user_id, Integer address_id, CreateOrUpdateAddressRequest request) {
        Address address = getUserAddress(user_id, address_id);

        if (request.title() != null) address.setTitle(request.title());
        if (request.country() != null) address.setCountry(request.country());
        if (request.city() != null) address.setCity(request.city());
        if (request.street() != null) address.setStreet(request.street());
        if (request.buildingNumber() != null) address.setBuildingNumber(request.buildingNumber());

        addressRepository.save(address);
        log.info("Address added successfully.");
    }

    @Transactional
    public void deleteAddress(Integer user_id, Integer address_id) {
        // just to check if the address exists and is owned by the defined user.
        Address address = getUserAddress(user_id, address_id);
        addressRepository.delete(address); // No extra DB call
        log.info("Address deleted successfully");
    }
}
