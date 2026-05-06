package io.github.pauliustechin.freelancer_marketplace.model.bid.dto;

import io.github.pauliustechin.freelancer_marketplace.model.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.model.user.User;
import io.github.pauliustechin.freelancer_marketplace.model.user.dto.FreelancerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BidMapper {

    Bid createBidToBid(CreateBidRequest request);

    @Mapping(source = "id", target = "bidId")
    @Mapping(source = "bidder", target = "freelancer")
    ClientBidResponse bidToClientBidResponse(Bid bid);

    @Mapping(source = "id", target = "bidId")
    BidResponse bidToBidResponse(Bid bid);

}
