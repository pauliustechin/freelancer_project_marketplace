package io.github.pauliustechin.freelancer_marketplace.model.contract.dto;

import io.github.pauliustechin.freelancer_marketplace.model.contract.EscrowStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ContractResponse {

    private Long contractId;

    private EscrowStatus escrowStatus;

    private BigDecimal agreedAmount;

    private BigDecimal escrowAmount;

    private String projectName;

}
