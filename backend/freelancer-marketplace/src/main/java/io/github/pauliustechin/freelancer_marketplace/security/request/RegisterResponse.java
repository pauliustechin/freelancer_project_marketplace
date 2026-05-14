package io.github.pauliustechin.freelancer_marketplace.security.request;

import io.github.pauliustechin.freelancer_marketplace.feature.role.AppRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RegisterResponse {

    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Set<AppRole> roleNames;

}
