package io.github.pauliustechin.freelancer_marketplace.contract;

import io.github.pauliustechin.freelancer_marketplace.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.contract.dto.ContractResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ContractController {

    private final ContractService contractService;

    @PatchMapping("/bids/{bidId}/contracts")
    public ResponseEntity<ContractResponse> confirmContract (@PathVariable Long bidId,
                                                             @RequestParam BidStatus status) {

        ContractResponse response = contractService.confirmContract(bidId, status);

        return ResponseEntity.ok().body(response);
    }

}
