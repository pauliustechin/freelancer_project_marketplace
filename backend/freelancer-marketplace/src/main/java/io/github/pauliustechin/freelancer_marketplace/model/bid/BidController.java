package io.github.pauliustechin.freelancer_marketplace.model.bid;

import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.*;
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

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/users/{userId}/bids")
    public ResponseEntity<BidListResponse> getFreelancerBids(@PathVariable Long userId) {

        BidListResponse response = bidService.getFreelancerBids(userId);

        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/projects/{projectId}/bids")
    public ResponseEntity<ClientBidListResponse> getBidsByProject(@PathVariable Long projectId) {

        ClientBidListResponse response = bidService.getBidsByProject(projectId);

        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/projects/{projectId}/bids")
    public ResponseEntity<BidResponse> createBid(@PathVariable Long projectId,
                                                 @Valid @RequestBody CreateBidRequest request,
                                                 Authentication authentication) {

        BidResponse response = bidService.createBid(projectId, request, authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('SELLER')")
    @PutMapping("/bids/{bidId}")
    public ResponseEntity<BidResponse> updateBid(@PathVariable Long bidId,
                                                 @Valid @RequestBody UpdateBidRequest request) {

        BidResponse response = bidService.updateBid(bidId, request);

        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping("/bids/{bidId}")
    public ResponseEntity<ClientBidResponse> updateBidStatus(@PathVariable Long bidId,
                                                       @RequestParam BidStatus status) {

        ClientBidResponse response = bidService.updateBidStatus(bidId, status);

        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('SELLER')")
    @DeleteMapping("/bids/{bidId}")
    public ResponseEntity<Void> deleteBid(@PathVariable Long bidId) {

        bidService.deleteBid(bidId);

        return ResponseEntity.noContent().build();
    }

}
