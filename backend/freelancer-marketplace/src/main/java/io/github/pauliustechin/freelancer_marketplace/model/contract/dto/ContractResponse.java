package io.github.pauliustechin.freelancer_marketplace.model.contract.dto;

import io.github.pauliustechin.freelancer_marketplace.model.contract.EscrowStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ContractResponse {

    private Long contractId;

    private EscrowStatus escrowStatus;

    private BigDecimal agreedAmount;

    private BigDecimal escrowAmount;



}
