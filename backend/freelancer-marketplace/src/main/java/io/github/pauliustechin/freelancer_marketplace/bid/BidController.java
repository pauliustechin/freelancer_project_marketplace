package io.github.pauliustechin.freelancer_marketplace.bid;

import io.github.pauliustechin.freelancer_marketplace.bid.dto.BidListResponse;
import io.github.pauliustechin.freelancer_marketplace.bid.dto.BidResponse;
import io.github.pauliustechin.freelancer_marketplace.bid.dto.CreateBidRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BidController {

    private final BidService bidService;

    @GetMapping("/projects/{projectId}/bids")
    public ResponseEntity<BidListResponse> getBidsByProject(@PathVariable Long projectId) {

        BidListResponse response = bidService.getBidsByProject(projectId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/projects/{projectId}/bids")
    public ResponseEntity<BidResponse> createBid(@PathVariable Long projectId,
                                                 @Valid @RequestBody CreateBidRequest request) {

        BidResponse response = bidService.createBid(projectId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
