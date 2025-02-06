package com.digitinary.DStore.model.mapper;

import com.digitinary.DStore.model.entity.User;
import com.digitinary.DStore.model.request.CreateUserRequest;

import com.digitinary.DStore.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registeredDate", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "password", expression = "java( passwordEncoder.encode(request.password()) )")
    public abstract User toUser(CreateUserRequest request);

//    @Mapping(target = "cart", ignore = true)
//    @Mapping(target = "orders", ignore = true)
//    @Mapping(target = "addresses", ignore = true)
//    @Mapping(target = "password", ignore = true)
    public abstract UserResponse toView(User user);

}
