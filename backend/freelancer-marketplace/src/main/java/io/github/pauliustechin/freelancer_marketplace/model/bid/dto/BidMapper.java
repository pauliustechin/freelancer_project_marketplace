package io.github.pauliustechin.freelancer_marketplace.model.bid.dto;

import io.github.pauliustechin.freelancer_marketplace.model.bid.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BidMapper {

    Bid createBidToBid(CreateBidRequest request);

    @Mapping(source = "id", target = "bidId")
    @Mapping(source = "bidder.id", target = "bidderId")
    BidResponse bidToBidResponse(Bid bid);

}
