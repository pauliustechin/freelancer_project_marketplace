package io.github.pauliustechin.freelancer_marketplace.model.bid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Modifying
    @Query("DELETE FROM Bid b WHERE b.project.id = :projectId")
    void deleteByProjectId(Long projectId);

    List<Bid> findBidsByProjectId(Long projectId);

    boolean existsByBidderIdAndProjectId(Long userId, Long projectId);

    @Query("SELECT b.project.id, COUNT(b) FROM Bid b WHERE b.project.id IN :projectIds GROUP BY b.project.id")
    List<Object[]> getProjectsWithBidCount(@Param("projectIds") Set<Long> projectIds);

}
