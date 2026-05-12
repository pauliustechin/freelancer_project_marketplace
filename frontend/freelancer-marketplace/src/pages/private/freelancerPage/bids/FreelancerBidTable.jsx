import useBidsStore from "../../../../store/bidsStore";
import useUsersStore from "../../../../store/usersStore";
import FreelancerBidRow from "./FreelancerBidRow";
import ConfirmationModal from "../../../../components/shared/ConfirmationModal";
import { useState, useEffect } from "react";

const FreelancerBidTable = () => {
  
  const { user } = useUsersStore((state) => state);
  const { fetchFreelancerBids, freelancerBids } = useBidsStore(
    (state) => state,
  );

  const { updateBid } = useBidsStore((state) => state);
  const [modal, setModal] = useState(null);

  useEffect(() => {
    fetchFreelancerBids(user.userId);
  }, [fetchFreelancerBids, user.userId]);

  return (
    <>
      <div className="overflow-x-auto">
        <h1 className="text-start text-2xl font-bold p-2">Recent bids</h1>
        <table className="table bg-slate-700">
          <thead className="text-gray-400">
            <tr>
              <th></th>
              <th>PROJECT</th>
              <th className="text-center">MY BID</th>
              <th>STATUS</th>
              <th>START DATE</th>
              <th className="text-end">ACTIONS</th>
            </tr>
          </thead>
          <tbody>
            {freelancerBids.map((bid, index) => (
              <FreelancerBidRow
                key={bid.bidId}
                bid={bid}
                index={index}
                setModal={setModal}
                updateBid={updateBid}
              ></FreelancerBidRow>
            ))}
          </tbody>
        </table>
        <ConfirmationModal
          modal={modal}
          setModal={setModal}
        ></ConfirmationModal>
      </div>
    </>
  );
};

export default FreelancerBidTable;
