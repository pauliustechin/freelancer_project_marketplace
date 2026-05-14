package io.github.pauliustechin.freelancer_marketplace.feature.user;

import io.github.pauliustechin.freelancer_marketplace.feature.user.dto.FreelancerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name = "Public APIs")
    @Operation(summary = "Get all freelancers")
    @GetMapping("/freelancers")
    public ResponseEntity<List<FreelancerResponse>> getAllFreelancers() {

        List<FreelancerResponse> freelancers = userService.getAllFreelancers();

        return ResponseEntity.ok().body(freelancers);
    }

}
