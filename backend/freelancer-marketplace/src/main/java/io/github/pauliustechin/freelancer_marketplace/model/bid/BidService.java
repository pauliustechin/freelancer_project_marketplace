package io.github.pauliustechin.freelancer_marketplace.model.bid;

import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.BidListResponse;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.BidResponse;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.CreateBidRequest;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.UpdateBidRequest;
import org.springframework.security.core.Authentication;

public interface BidService {

    BidListResponse getBidsByProject(Long projectId);
    BidResponse createBid(Long projectId, CreateBidRequest createRequest, Authentication authentication);
    BidResponse updateBid(Long bidId, UpdateBidRequest request);
    BidResponse updateBidStatus(Long bidId, BidStatus status);

}
