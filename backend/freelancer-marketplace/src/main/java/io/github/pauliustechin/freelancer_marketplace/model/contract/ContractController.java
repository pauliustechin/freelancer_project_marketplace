package io.github.pauliustechin.freelancer_marketplace.model.contract;

import io.github.pauliustechin.freelancer_marketplace.model.contract.dto.ContractListResponse;
import io.github.pauliustechin.freelancer_marketplace.security.service.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ContractController {

    private final ContractService contractService;

    @Tag(name = "Freelancer APIs")
    @Operation(summary = "Get freelancer contracts")
    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/contracts/freelancer")
    public ResponseEntity<ContractListResponse> getFreelancerContracts(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ContractListResponse response = contractService.getBidderContracts(userDetails.getId());

        return ResponseEntity.ok().body(response);
    }

    @Tag(name = "Client APIs")
    @Operation(summary = "Get client contracts")
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/contracts/client")
    public ResponseEntity<ContractListResponse> getClientContracts(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ContractListResponse response = contractService.getClientContracts(userDetails.getId());

        return ResponseEntity.ok().body(response);
    }



}
