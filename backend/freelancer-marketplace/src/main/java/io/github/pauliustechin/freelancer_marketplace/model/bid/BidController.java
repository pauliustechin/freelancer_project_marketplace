package io.github.pauliustechin.freelancer_marketplace.model.bid;

import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BidController {

    private final BidService bidService;

    @Tag(name = "Freelancer APIs", description = "APIs for managing freelancer requests")
    @Operation(summary = "Get all freelancer's bids")
    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/users/{userId}/bids")
    public ResponseEntity<BidListResponse> getFreelancerBids(@PathVariable Long userId) {

        BidListResponse response = bidService.getFreelancerBids(userId);

        return ResponseEntity.ok().body(response);
    }

    @Tag(name = "Client APIs", description = "APIs for managing client requests")
    @Operation(summary = "Get client bid by project")
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/projects/{projectId}/bids")
    public ResponseEntity<ClientBidListResponse> getBidsByProject(@PathVariable Long projectId) {

        ClientBidListResponse response = bidService.getBidsByProject(projectId);

        return ResponseEntity.ok().body(response);
    }

    @Tag(name = "Freelancer APIs")
    @Operation(summary = "Create Bid")
    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/projects/{projectId}/bids")
    public ResponseEntity<BidResponse> createBid(@PathVariable Long projectId,
                                                 @Valid @RequestBody CreateBidRequest request,
                                                 Authentication authentication) {

        BidResponse response = bidService.createBid(projectId, request, authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Tag(name = "Freelancer APIs")
    @Operation(summary = "Update Bid")
    @PreAuthorize("hasRole('SELLER')")
    @PutMapping("/bids/{bidId}")
    public ResponseEntity<BidResponse> updateBid(@PathVariable Long bidId,
                                                 @Valid @RequestBody UpdateBidRequest request) {

        BidResponse response = bidService.updateBid(bidId, request);

        return ResponseEntity.ok().body(response);
    }

    @Tag(name = "Client APIs")
    @Operation(summary = "Update bid status", description = "Client can only accept freelancer's bid.")
    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping("/bids/{bidId}")
    public ResponseEntity<ClientBidResponse> updateBidStatus(@PathVariable Long bidId,
                                                       @RequestParam BidStatus status) {

        ClientBidResponse response = bidService.updateBidStatus(bidId, status);

        return ResponseEntity.ok().body(response);
    }

    @Tag(name = "Freelancer APIs")
    @Operation(summary = "Deleted Bid")
    @PreAuthorize("hasRole('SELLER')")
    @DeleteMapping("/bids/{bidId}")
    public ResponseEntity<Void> deleteBid(@PathVariable Long bidId) {

        bidService.deleteBid(bidId);

        return ResponseEntity.noContent().build();
    }

}
