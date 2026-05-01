package io.github.pauliustechin.freelancer_marketplace.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private Long userId;

    private String username;

    private String firstName;

    private String lastName;

    private String email;
}
