package io.github.pauliustechin.freelancer_marketplace.security.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {

    private String username;
    private List<String> roles;

}
