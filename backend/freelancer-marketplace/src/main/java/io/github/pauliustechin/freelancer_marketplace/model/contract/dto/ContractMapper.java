package io.github.pauliustechin.freelancer_marketplace.model.contract.dto;

import io.github.pauliustechin.freelancer_marketplace.model.contract.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(source = "id", target = "contractId")
    ContractResponse contractToContractResponse(Contract contract);

}
