import useBidsStore from "../../../store/bidsStore";
import useUsersStore from "../../../store/usersStore";
import { useEffect } from "react";
import FreelancerBidRow from "./FreelancerBidRow";

const FreelancerPage = () => {

  const { user } = useUsersStore(state => state);
  const { fetchFreelancerBids, freelancerBids } = useBidsStore(state => state);

  useEffect(() => {
    fetchFreelancerBids(user.userId);
  }, [fetchFreelancerBids, user.userId])

  return (
    <div>Freelancer Page asdfasdf
      <div className="overflow-x-auto">
      <table className="table">
        <thead>
          <tr>
            <th></th>
            <th>Project Name</th>
            <th>Project Start</th>
            <th>BidStatus</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
        {freelancerBids.map((bid, index) => <FreelancerBidRow key={bid.bidId} bid={bid} index={index}></FreelancerBidRow>)}
        </tbody>
      </table>
    </div>
    </div>
  )
};

export default FreelancerPage;
