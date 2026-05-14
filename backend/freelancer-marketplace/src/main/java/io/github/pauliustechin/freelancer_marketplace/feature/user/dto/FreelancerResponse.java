package io.github.pauliustechin.freelancer_marketplace.feature.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FreelancerResponse {

    private Long freelancerId;
    private String firstName;
    private String lastName;
    private String email;

}
