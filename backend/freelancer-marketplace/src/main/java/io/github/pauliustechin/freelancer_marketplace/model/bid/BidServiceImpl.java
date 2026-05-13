package io.github.pauliustechin.freelancer_marketplace.model.bid;

import io.github.pauliustechin.freelancer_marketplace.exception.NotAllowedApiActionException;
import io.github.pauliustechin.freelancer_marketplace.model.bid.dto.*;
import io.github.pauliustechin.freelancer_marketplace.model.contract.ContractService;
import io.github.pauliustechin.freelancer_marketplace.exception.DuplicateBidException;
import io.github.pauliustechin.freelancer_marketplace.exception.IllegalBidStateException;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.model.project.Project;
import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectRepository;
import io.github.pauliustechin.freelancer_marketplace.model.project.ProjectStatus;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectMapper;
import io.github.pauliustechin.freelancer_marketplace.model.project.dto.ProjectSummaryResponse;
import io.github.pauliustechin.freelancer_marketplace.model.role.AppRole;
import io.github.pauliustechin.freelancer_marketplace.model.user.User;
import io.github.pauliustechin.freelancer_marketplace.model.user.UserRepository;
import io.github.pauliustechin.freelancer_marketplace.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService{

    private static final Logger logger = LoggerFactory.getLogger(BidServiceImpl.class);

    private final BidRepository bidRepository;
    private final BidMapper bidMapper;
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ContractService contractService;

    @Override
    public BidListResponse getFreelancerBids(Long userId) {

        List<Bid> bids = bidRepository.findBidsByBidderId(userId);

        List<BidResponse> bidResponses = bids.stream()
                .map(bid -> {
                    Project project = bid.getProject();
                    ProjectSummaryResponse projectSummary = projectMapper.projectToProjectSummaryResponse(project);
                    BidResponse response = bidMapper.bidToBidResponse(bid);
                    response.setProjectSummary(projectSummary);
                    return response;
                })
                .toList();

        return new BidListResponse(bidResponses);
    }

    @Override
    public void deleteBid(Long bidId) {

        if(bidId == null) {
            throw new ResourceNotFoundException("BidId");
        }

        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid", bidId));

        if(!bid.getBidStatus().equals(BidStatus.OPEN)) {
            throw new IllegalBidStateException("A bid can only be deleted when it is OPEN.");
        }

        bidRepository.delete(bid);

    }

    @Override
    public ClientBidListResponse getBidsByProject(Long projectId) {

        if(projectId == null) {
            throw new ResourceNotFoundException("ProjectId");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId));

        List<Bid> bids = bidRepository.findBidsByProjectId(projectId);

        List<ClientBidResponse> bidResponses = bids.stream()
                .map(bid -> bidMapper.bidToClientBidResponse(bid))
                .toList();

        return new ClientBidListResponse(bidResponses);
    }

    @Transactional
    @Override
    public BidResponse createBid(Long projectId, CreateBidRequest createRequest, Authentication authentication) {

        if(projectId == null) {
            throw new ResourceNotFoundException("Project", projectId);
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        boolean isSeller = userDetails.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals(AppRole.ROLE_SELLER.name()));
        if (!isSeller) {
            throw new NotAllowedApiActionException("Only freelancers are allowed to place a bid");
        }


        if(bidRepository.existsByBidderIdAndProjectId(userDetails.getId(), projectId)) {
            throw new DuplicateBidException(userDetails.getId());
        }

        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bidder", userDetails.getId()));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId));

        Bid bid = bidMapper.createBidToBid(createRequest);
        bid.setCreatedAt(Instant.now());
        bid.setProject(project);
        bid.setBidStatus(BidStatus.OPEN);
        bid.setBidder(user);
        Bid savedBid = bidRepository.save(bid);

        BidResponse bidResponse = bidMapper.bidToBidResponse(savedBid);
        ProjectSummaryResponse projectSummaryResponse = projectMapper.projectToProjectSummaryResponse(project);
        bidResponse.setProjectSummary(projectSummaryResponse);

        logger.info("Bid created successfully for projectId={}, userId={}, bidId={}", projectId, user.getId(), bidResponse.getBidId());

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
        BidStatus reqStatus = request.getStatus();
        BidStatus bidStatus = bid.getBidStatus();

        Project project = projectRepository.findById(bid.getProject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project", bid.getProject().getId()));

        ProjectSummaryResponse projectSummary = projectMapper.projectToProjectSummaryResponse(project);

        if(bidStatus.equals(BidStatus.PENDING)) {
            if((reqStatus.equals(BidStatus.CONFIRMED)) || (reqStatus.equals(BidStatus.CANCELED))) {
                Bid bidAfterDecision = contractService.confirmContract(reqStatus, bid);
                BidResponse response = bidMapper.bidToBidResponse(bidAfterDecision);
                response.setProjectSummary(projectSummary);
                return response;
            } else {
                throw new IllegalBidStateException("Bid is accepted by client and waiting for bidder confirmation.");
            }
        }

        if(bidStatus.equals(BidStatus.CANCELED) || bidStatus.equals(BidStatus.CONFIRMED)) {
            throw new IllegalBidStateException(bidId);

        } else if(reqStatus.equals(BidStatus.CONFIRMED)) {
            throw new IllegalBidStateException(request.getStatus());

        } else if(!(reqStatus.equals(BidStatus.CANCELED)) && !(reqStatus.equals(BidStatus.OPEN))){
            throw new IllegalBidStateException(request.getStatus());
        }

        bid.setUpdatedAt(Instant.now());
        bid.setBidStatus(request.getStatus());
        bid.setAmount(request.getAmount());

        Bid savedBid = bidRepository.save(bid);
        BidResponse response = bidMapper.bidToBidResponse(savedBid);

        response.setProjectSummary(projectSummary);

        logger.info("Bid updated successfully for bidId={}.", savedBid.getId());

        return response;
    }

    @Transactional
    @Override
    public ClientBidResponse updateBidStatus(Long bidId, BidStatus status) {

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

        project.setProjectStatus(ProjectStatus.PENDING);
        projectRepository.save(project);

        bid.setUpdatedAt(Instant.now());
        bid.setBidStatus(BidStatus.PENDING);
        Bid savedBid = bidRepository.save(bid);

        ClientBidResponse response = bidMapper.bidToClientBidResponse(savedBid);

        logger.info("Bid status updated successfully for bidId={}.", savedBid.getId());

        return response;
    }


}
