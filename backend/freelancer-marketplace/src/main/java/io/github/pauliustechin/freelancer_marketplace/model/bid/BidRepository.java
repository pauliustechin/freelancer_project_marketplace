package io.github.pauliustechin.freelancer_marketplace.model.bid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Modifying
    @Query("DELETE FROM Bid b WHERE b.project.id = :projectId")
    void deleteByProjectId(Long projectId);

    List<Bid> findBidsByProjectId(Long projectId);

    boolean existsByProjectId(Long projectId);

}
