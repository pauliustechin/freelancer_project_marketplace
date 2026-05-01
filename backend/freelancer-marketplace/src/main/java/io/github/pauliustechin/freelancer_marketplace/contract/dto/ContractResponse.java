package io.github.pauliustechin.freelancer_marketplace.contract.dto;

import io.github.pauliustechin.freelancer_marketplace.contract.EscrowStatus;
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
