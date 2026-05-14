package io.github.pauliustechin.freelancer_marketplace.feature.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("""
    SELECT c
    FROM Contract c
    JOIN c.bid b
    WHERE b.bidder.id = :bidderId
    """)
    List<Contract> findContractsByBidderId(@Param("bidderId") Long bidderId);

    @Query("""
    SELECT c
    FROM Contract c
    JOIN c.bid b
    JOIN b.project p
    JOIN p.client cl
    WHERE cl.id = :clientId
    """)
    List<Contract> findContractsByClientId(@Param("clientId") Long clientId);

}
