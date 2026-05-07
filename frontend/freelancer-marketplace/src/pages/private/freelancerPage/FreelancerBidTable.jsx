import useBidsStore from "../../../store/bidsStore";
import useUsersStore from "../../../store/usersStore";
import FreelancerBidRow from "./FreelancerBidRow";
import ConfirmationModal from "../../../components/shared/ConfirmationModal";
import { useState, useEffect } from "react";
import { ConfirmationStatus } from "../../../enums/confirmationStatus";

const FreelancerBidTable = () => {
  const { user } = useUsersStore((state) => state);
  const { fetchFreelancerBids, freelancerBids } = useBidsStore(
    (state) => state,
  );

  const { updateBid } = useBidsStore((state) => state);

  const [open, setOpen] = useState(false);
  const [message, setMessage] = useState(false);
  const [status, setStatus] = useState(ConfirmationStatus.WAITING);
  const [bid, setBid] = useState({
    bidId: "",
    amount: 0,
  });

  useEffect(() => {
  const updateBidStatus = async () => {
    if (status === ConfirmationStatus.ACCEPTED) {
      await updateBid(bid.bidId, {
        amount: bid.amount,
        status: "CONFIRMED",
      });

      await fetchFreelancerBids(user.userId);

    } else if (status === ConfirmationStatus.REJECTED) {
      await updateBid(bid.bidId, {
        amount: bid.amount,
        status: "CANCELED",
      });

      await fetchFreelancerBids(user.userId);
    }
  };
  updateBidStatus();
  }, [status]);

  useEffect(() => {
    fetchFreelancerBids(user.userId);
  }, [fetchFreelancerBids, user.userId]);

  return (
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
            {freelancerBids.map((bid, index) => (
              <FreelancerBidRow
                key={bid.bidId}
                bid={bid}
                index={index}
                setMessage={setMessage}
                setBid={setBid}
                setOpen={setOpen}
              ></FreelancerBidRow>
            ))}
          </tbody>
        </table>
        <ConfirmationModal
          open={open}
          setOpen={setOpen}
          message={message}
          confirmButton={"Confirm"}
          rejectButton={"Reject"}
          setStatus={setStatus}
        ></ConfirmationModal>
      </div>
  );
};

export default FreelancerBidTable;
