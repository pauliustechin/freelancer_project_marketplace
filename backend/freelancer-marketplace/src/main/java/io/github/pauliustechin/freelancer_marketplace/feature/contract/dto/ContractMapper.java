package io.github.pauliustechin.freelancer_marketplace.feature.contract.dto;

import io.github.pauliustechin.freelancer_marketplace.feature.contract.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(source = "id", target = "contractId")
    ContractResponse contractToContractResponse(Contract contract);

}
