package io.github.pauliustechin.freelancer_marketplace.model.user.dto;

import io.github.pauliustechin.freelancer_marketplace.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User createUserToUser(CreateUserRequest request);

    @Mapping(source = "id", target = "userId")
    UserResponse userToUserResponse(User user);

}


