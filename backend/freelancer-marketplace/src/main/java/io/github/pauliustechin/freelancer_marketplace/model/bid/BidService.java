package io.github.pauliustechin.freelancer_marketplace.model.bid;

import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.BidListResponse;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.BidResponse;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.CreateBidRequest;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.UpdateBidRequest;

public interface BidService {

    BidListResponse getBidsByProject(Long projectId);
    BidResponse createBid(Long projectId, CreateBidRequest createRequest);
    BidResponse updateBid(Long bidId, UpdateBidRequest request);
    BidResponse updateBidStatus(Long bidId, BidStatus status);

}
