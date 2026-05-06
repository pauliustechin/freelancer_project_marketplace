package io.github.pauliustechin.freelancer_marketplace.model.bid;

import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.*;
import org.springframework.security.core.Authentication;

public interface BidService {

    ClientBidListResponse getBidsByProject(Long projectId);
    BidResponse createBid(Long projectId, CreateBidRequest createRequest, Authentication authentication);
    BidResponse updateBid(Long bidId, UpdateBidRequest request);
    ClientBidResponse updateBidStatus(Long bidId, BidStatus status);
    BidListResponse getFreelancerBids(Long userId);
}
