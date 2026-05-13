package io.github.pauliustechin.freelancer_marketplace.model.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ContractListResponse {
    private List<ContractResponse> content;
}
