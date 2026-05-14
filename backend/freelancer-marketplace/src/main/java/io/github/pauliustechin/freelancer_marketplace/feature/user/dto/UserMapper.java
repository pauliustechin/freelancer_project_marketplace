package io.github.pauliustechin.freelancer_marketplace.feature.user.dto;

import io.github.pauliustechin.freelancer_marketplace.feature.user.User;
import io.github.pauliustechin.freelancer_marketplace.security.request.RegisterRequest;
import io.github.pauliustechin.freelancer_marketplace.security.request.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registerUserToUser(RegisterRequest request);

    @Mapping(source = "id", target = "userId")
    RegisterResponse userRegisterToResponse(User user);

    @Mapping(source = "id", target = "freelancerId")
    FreelancerResponse userToFreelancerResponse(User user);

}


