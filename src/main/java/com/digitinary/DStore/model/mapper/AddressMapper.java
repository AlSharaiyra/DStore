package com.digitinary.DStore.model.mapper;

import com.digitinary.DStore.repository.entity.Address;
import com.digitinary.DStore.repository.entity.User;
import com.digitinary.DStore.model.request.CreateOrUpdateAddressRequest;
import com.digitinary.DStore.model.response.AddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    public abstract Address toAddress(CreateOrUpdateAddressRequest request);

    @Mapping(target = "user_id", source = "user", qualifiedByName = "userToUserId")
    public abstract AddressResponse toView(Address address);

    @Named("userToUserId")
    protected Integer userToUserId(User user){
        return user.getId();
    }
}
