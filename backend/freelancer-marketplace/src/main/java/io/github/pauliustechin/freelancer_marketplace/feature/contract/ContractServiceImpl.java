package io.github.pauliustechin.freelancer_marketplace.feature.contract;

import io.github.pauliustechin.freelancer_marketplace.feature.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.feature.bid.BidRepository;
import io.github.pauliustechin.freelancer_marketplace.feature.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.feature.contract.dto.ContractListResponse;
import io.github.pauliustechin.freelancer_marketplace.feature.contract.dto.ContractMapper;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.feature.contract.dto.ContractResponse;
import io.github.pauliustechin.freelancer_marketplace.feature.project.Project;
import io.github.pauliustechin.freelancer_marketplace.feature.project.ProjectRepository;
import io.github.pauliustechin.freelancer_marketplace.feature.project.ProjectStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

    private final BidRepository bidRepository;
    private final ContractMapper contractMapper;
    private final ContractRepository contractRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public Bid confirmContract(BidStatus status, Bid bidFromDb) {

        Project project = projectRepository.findById(bidFromDb.getProject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project", bidFromDb.getProject().getId()));

        if(status.equals(BidStatus.CANCELED)) {
            project.setProjectStatus(ProjectStatus.OPEN);
            projectRepository.save(project);

            bidFromDb.setUpdatedAt(Instant.now());
            bidFromDb.setBidStatus(BidStatus.CANCELED);
            return bidRepository.save(bidFromDb);
        }

        List<Bid> bids = bidRepository.findBidsByProjectId(project.getId());

        for(Bid b : bids) {
            if(!b.equals(bidFromDb)) {
                b.setUpdatedAt(Instant.now());
                if (b.getBidStatus() == BidStatus.OPEN) {
                    b.setBidStatus(BidStatus.REJECTED);
                }
                bidRepository.save(b);
            }
        }

        Contract contract = new Contract();
        contract.setBid(bidFromDb);
        contract.setAgreedAmount(bidFromDb.getAmount());
        contract.setEscrowStatus(EscrowStatus.PENDING);
        contract.setCreatedAt(Instant.now());
        contract.setEscrowAmount(BigDecimal.ZERO);
        Contract savedContract = contractRepository.save(contract);

        project.setProjectStatus(ProjectStatus.IN_PROGRESS);
        projectRepository.save(project);

        bidFromDb.setBidStatus(BidStatus.CONFIRMED);
        Bid savedBid = bidRepository.save(bidFromDb);

        logger.info("Freelancer accepted contract freelancerId={}, bidId={}, contractId={}, ",
                bidFromDb.getBidder().getId(), bidFromDb.getId(), savedContract.getId());

        return savedBid;
    }

    @Transactional
    @Override
    public ContractListResponse getBidderContracts(Long bidderId) {

        List<Contract> contracts = contractRepository.findContractsByBidderId(bidderId);

        List<ContractResponse> response = contracts.stream()
                .map(contract -> {
                    ContractResponse cr = contractMapper.contractToContractResponse(contract);
                    cr.setProjectName(contract.getBid().getProject().getProjectName());
                    return cr;
                })
                .toList();

        return new ContractListResponse(response);
    }

    @Transactional
    @Override
    public ContractListResponse getClientContracts(Long clientId) {

        List<Contract> contracts = contractRepository.findContractsByClientId(clientId);

        List<ContractResponse> response = contracts.stream()
                .map(contract -> {
                    ContractResponse cr = contractMapper.contractToContractResponse(contract);
                    cr.setProjectName(contract.getBid().getProject().getProjectName());
                    return cr;
                })
                .toList();

        return new ContractListResponse(response);
    }
}
