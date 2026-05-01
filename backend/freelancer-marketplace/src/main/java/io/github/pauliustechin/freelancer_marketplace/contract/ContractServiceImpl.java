package io.github.pauliustechin.freelancer_marketplace.contract;

import io.github.pauliustechin.freelancer_marketplace.bid.Bid;
import io.github.pauliustechin.freelancer_marketplace.bid.BidRepository;
import io.github.pauliustechin.freelancer_marketplace.bid.BidStatus;
import io.github.pauliustechin.freelancer_marketplace.contract.dto.ContractMapper;
import io.github.pauliustechin.freelancer_marketplace.contract.dto.ContractResponse;
import io.github.pauliustechin.freelancer_marketplace.exception.ContractRejectedException;
import io.github.pauliustechin.freelancer_marketplace.exception.IllegalBidStateException;
import io.github.pauliustechin.freelancer_marketplace.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final BidRepository bidRepository;
    private final ContractMapper contractMapper;
    private final ContractRepository contractRepository;

    @Transactional
    @Override
    public ContractResponse confirmContract(Long bidId, BidStatus bidStatus) {

        if(bidStatus.equals(BidStatus.CANCELED)) {
            throw new ContractRejectedException();
        }

        if(bidStatus == null || !(bidStatus.equals(BidStatus.CONFIRMED))) {
            throw new IllegalBidStateException(bidStatus);
        }

        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid", bidId));

        bid.setBidStatus(BidStatus.CONFIRMED);
        bidRepository.save(bid);

        Contract contract = new Contract();
        contract.setBid(bid);
        contract.setAgreedAmount(bid.getAmount());
        contract.setEscrowStatus(EscrowStatus.PENDING);
        contract.setCreatedAt(Instant.now());
        contract.setEscrowAmount(BigDecimal.ZERO);

        Contract savedContract = contractRepository.save(contract);

        return contractMapper.contractToContractResponse(savedContract);
    }
}
