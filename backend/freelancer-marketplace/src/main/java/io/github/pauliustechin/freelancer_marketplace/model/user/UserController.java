package io.github.pauliustechin.freelancer_marketplace.model.user;

import io.github.pauliustechin.freelancer_marketplace.model.user.dto.FreelancerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/freelancers")
    public ResponseEntity<List<FreelancerResponse>> getAllFreelancers() {

        List<FreelancerResponse> freelancers = userService.getAllFreelancers();

        return ResponseEntity.ok().body(freelancers);
    }

}
