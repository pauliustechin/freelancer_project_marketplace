package io.github.pauliustechin.freelancer_marketplace.bid;

import io.github.pauliustechin.freelancer_marketplace.bid.dto.BidListResponse;
import io.github.pauliustechin.freelancer_marketplace.bid.dto.BidMapper;
import io.github.pauliustechin.freelancer_marketplace.bid.dto.BidResponse;
import io.github.pauliustechin.freelancer_marketplace.bid.dto.CreateBidRequest;
import io.github.pauliustechin.freelancer_marketplace.exception.ProjectNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.project.Project;
import io.github.pauliustechin.freelancer_marketplace.project.ProjectRepository;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectMapper;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService{

    private final BidRepository bidRepository;
    private final BidMapper bidMapper;
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    @Override
    public BidListResponse getBidsByProject(Long projectId) {

        List<Bid> bids = bidRepository.findBidsByProjectId(projectId);
        List<BidResponse> bidResponses = bids.stream()
                .map(bid -> bidMapper.bidToBidResponse(bid))
                .toList();

        return new BidListResponse(bidResponses);
    }

    @Transactional
    @Override
    public BidResponse createBid(Long projectId, CreateBidRequest createRequest) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        Bid bid = bidMapper.createBidToBid(createRequest);
        bid.setCreatedAt(Instant.now());
        bid.setProject(project);
        bid.setBidStatus(BidStatus.PENDING);
        Bid savedBid = bidRepository.save(bid);

        BidResponse bidResponse = bidMapper.bidToBidResponse(savedBid);
        ProjectSummaryResponse projectSummaryResponse = projectMapper.projectToProjectSummaryResponse(project);
        bidResponse.setProjectSummaryResponse(projectSummaryResponse);

        return bidResponse;
    }
}
