import ConfirmationModal from "../../../components/shared/ConfirmationModal";
import { useState, useEffect } from "react";
import useBidsStore from "../../../store/bidsStore";
import { ConfirmationStatus } from "../../../enums/confirmationStatus";

const FreelancerBidRow = ({ bid, index }) => {

  const {
    amount,
    bidStatus,
    projectSummary: { projectName, projectStart }
  } = bid;

  const { updateBid } = useBidsStore(state => state);

  const [ open, setOpen ] = useState(false);
  const [ message, setMessage ] = useState(false);
  const [ status, setStatus ] = useState(ConfirmationStatus.WAITING);

  const handleOpen = () => {
    setMessage("Are you sure you want to confirm your bid? Once confirmed, this action cannot be undone.")
    setOpen(true);
  };

  useEffect(() => {
    if (status === ConfirmationStatus.ACCEPTED) {
      updateBid(bid.bidId, {
        "amount" : amount,
        "status" : "CONFIRMED"
      })
    } else if (status === ConfirmationStatus.REJECTED) {
        updateBid(bid.bidId, {
          "amount" : amount,
          "status" : "CANCELED"
        })
    }
  }, [status]);

  return (
    <>
      <tr>
        <th>{index + 1}</th>
        <td onClick={handleOpen} className="underline">{projectName}</td>
        <td>{projectStart}</td>
        <td>{bidStatus}</td>
        <td>{amount} $</td>
        <td>{bidStatus === "PENDING" &&
          <button 
            className={`btn btn-primary w-28 bg-green-400 font-bold text-white m-4 self-end`}
            onClick={handleOpen}
          >CONFIRM</button>}
        </td>
      </tr>
      <ConfirmationModal open={open} setOpen={setOpen} message={message} confirmButton={"Confirm"} rejectButton={"Reject"} setStatus={setStatus}></ConfirmationModal>
    </>
  );
};

export default FreelancerBidRow;
