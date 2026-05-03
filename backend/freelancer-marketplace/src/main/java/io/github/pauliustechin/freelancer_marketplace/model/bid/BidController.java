package io.github.pauliustechin.freelancer_marketplace.model.bid;

import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.BidListResponse;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.BidResponse;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.CreateBidRequest;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.UpdateBidRequest;
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

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/projects/{projectId}/bids")
    public ResponseEntity<BidResponse> createBid(@PathVariable Long projectId,
                                                 @Valid @RequestBody CreateBidRequest request) {

        BidResponse response = bidService.createBid(projectId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/bids/{bidId}")
    public ResponseEntity<BidResponse> updateBid(@PathVariable Long bidId,
                                                 @Valid @RequestBody UpdateBidRequest request) {

        BidResponse response = bidService.updateBid(bidId, request);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/bids/{bidId}")
    public ResponseEntity<BidResponse> updateBidStatus(@PathVariable Long bidId,
                                                       @RequestParam BidStatus status) {

        BidResponse response = bidService.updateBidStatus(bidId, status);

        return ResponseEntity.ok().body(response);
    }

}
