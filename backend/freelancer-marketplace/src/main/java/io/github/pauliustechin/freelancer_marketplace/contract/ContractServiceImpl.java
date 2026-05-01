package io.github.pauliustechin.freelancer_marketplace.contract;

import io.github.pauliustechin.freelancer_marketplace.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.bid.BidRepository;
import io.github.pauliustechin.freelancer_marketplace.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.contract.dto.ContractMapper;
import io.github.pauliustechin.freelancer_marketplace.exception.IllegalBidStateException;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import io.github.pauliustechin.freelancer_marketplace.project.Project;
import io.github.pauliustechin.freelancer_marketplace.project.ProjectRepository;
import io.github.pauliustechin.freelancer_marketplace.project.ProjectStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

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
                b.setBidStatus(BidStatus.REJECTED);
                bidRepository.save(b);
            }
        }

        Contract contract = new Contract();
        contract.setBid(bidFromDb);
        contract.setAgreedAmount(bidFromDb.getAmount());
        contract.setEscrowStatus(EscrowStatus.PENDING);
        contract.setCreatedAt(Instant.now());
        contract.setEscrowAmount(BigDecimal.ZERO);
        contractRepository.save(contract);

        project.setProjectStatus(ProjectStatus.IN_PROGRESS);
        projectRepository.save(project);

        bidFromDb.setBidStatus(BidStatus.CONFIRMED);
        return bidRepository.save(bidFromDb);
    }
}
