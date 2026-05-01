package io.github.pauliustechin.freelancer_marketplace.bid;

import io.github.pauliustechin.freelancer_marketplace.bid.dto.*;
import io.github.pauliustechin.freelancer_marketplace.exception.DuplicateBidException;
import io.github.pauliustechin.freelancer_marketplace.exception.IllegalBidStateException;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.project.Project;
import io.github.pauliustechin.freelancer_marketplace.project.ProjectRepository;
import io.github.pauliustechin.freelancer_marketplace.project.ProjectStatus;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectMapper;
import io.github.pauliustechin.freelancer_marketplace.project.dto.ProjectSummaryResponse;
import io.github.pauliustechin.freelancer_marketplace.user.User;
import io.github.pauliustechin.freelancer_marketplace.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService{

    private final BidRepository bidRepository;
    private final BidMapper bidMapper;
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public BidListResponse getBidsByProject(Long projectId) {

        if(projectId == null) {
            throw new ResourceNotFoundException("Project", projectId);
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId));

        ProjectSummaryResponse summaryResponse = projectMapper.projectToProjectSummaryResponse(project);

        List<Bid> bids = new ArrayList<>(bidRepository.findBidsByProjectId(projectId));

        List<BidResponse> bidResponses = bids.stream()
                .map(bid -> bidMapper.bidToBidResponse(bid))
                .map(br -> {
                    br.setProjectSummaryResponse(summaryResponse);
                    return br;
                })
                .toList();

        return new BidListResponse(bidResponses);
    }

    @Transactional
    @Override
    public BidResponse createBid(Long projectId, CreateBidRequest createRequest) {

        if(projectId == null) {
            throw new ResourceNotFoundException("Project", projectId);
        }

        if(bidRepository.existsByProjectId(projectId)) {
            throw new DuplicateBidException(projectId);
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId));

        User user = userRepository.findById(createRequest.getBidderId())
                .orElseThrow(() -> new ResourceNotFoundException("Bidder", createRequest.getBidderId()));

        Bid bid = bidMapper.createBidToBid(createRequest);
        bid.setCreatedAt(Instant.now());
        bid.setProject(project);
        bid.setBidStatus(BidStatus.PENDING);
        bid.setBidder(user);
        Bid savedBid = bidRepository.save(bid);

        BidResponse bidResponse = bidMapper.bidToBidResponse(savedBid);
        ProjectSummaryResponse projectSummaryResponse = projectMapper.projectToProjectSummaryResponse(project);
        bidResponse.setProjectSummaryResponse(projectSummaryResponse);
        bidResponse.setBidderId(user.getId());

        return bidResponse;
    }

    @Transactional
    @Override
    public BidResponse updateBid(Long bidId, UpdateBidRequest request) {

        if(bidId == null) {
            throw new ResourceNotFoundException("Bid", bidId);
        }

        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid", bidId));

        if(bid.getBidStatus().equals(BidStatus.CANCELED)) {
            throw new IllegalBidStateException(bidId);
        } else if(!(request.equals(BidStatus.CANCELED)) || !(request.equals(BidStatus.PENDING))) {
            throw new IllegalBidStateException(request.getStatus());
        }

        bid.setUpdatedAt(Instant.now());
        bid.setBidStatus(request.getStatus());
        bid.setAmount(request.getAmount());

        Bid savedBid = bidRepository.save(bid);

        return bidMapper.bidToBidResponse(savedBid);
    }

    @Transactional
    @Override
    public BidResponse updateBidStatus(Long bidId, BidStatus status) {

        if(bidId == null) {
            throw new ResourceNotFoundException("Bid", bidId);
        }

        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid", bidId));

        Project project = projectRepository.findById(bid.getProject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project", bid.getProject().getId()));

        if(bid.getBidStatus().equals(BidStatus.CANCELED) || bid.getBidStatus().equals(BidStatus.REJECTED)) {
            throw new IllegalBidStateException(bidId);
        } else if(!status.equals(BidStatus.ACCEPTED)) {
            throw new IllegalBidStateException();
        }

        List<Bid> bids = bidRepository.findBidsByProjectId(bid.getProject().getId());

        for(Bid b : bids) {
            if(!b.equals(bid)) {
                b.setUpdatedAt(Instant.now());
                b.setBidStatus(BidStatus.REJECTED);
                bidRepository.save(b);
            }
        }

        project.setProjectStatus(ProjectStatus.IN_PROGRESS);
        Project savedProjecet = projectRepository.save(project);

        bid.setUpdatedAt(Instant.now());
        bid.setBidStatus(status);
        Bid savedBid = bidRepository.save(bid);

        BidResponse response = bidMapper.bidToBidResponse(savedBid);
        response.setProjectSummaryResponse(projectMapper.projectToProjectSummaryResponse(savedProjecet));

        return response;
    }
}
