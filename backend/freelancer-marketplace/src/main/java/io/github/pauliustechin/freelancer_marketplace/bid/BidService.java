package io.github.pauliustechin.freelancer_marketplace.bid;

import io.github.pauliustechin.freelancer_marketplace.bid.dto.BidListResponse;
import io.github.pauliustechin.freelancer_marketplace.bid.dto.BidResponse;
import io.github.pauliustechin.freelancer_marketplace.bid.dto.CreateBidRequest;
import io.github.pauliustechin.freelancer_marketplace.bid.dto.UpdateBidRequest;

public interface BidService {

    BidListResponse getBidsByProject(Long projectId);
    BidResponse createBid(Long projectId, CreateBidRequest createRequest);
    BidResponse updateBid(Long bidId, UpdateBidRequest request);
    BidResponse updateBidStatus(Long bidId, BidStatus status);

}
