package io.github.pauliustechin.freelancer_marketplace.security.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseCookie;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccess {
    
    AuthResponse response;
    ResponseCookie cookie;

}
