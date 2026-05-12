import { MdEdit } from "react-icons/md";
import { MdDeleteOutline } from "react-icons/md";
import { BidStatus } from "../../../../enums/bidStatus";

const FreelancerBidRow = ({ bid, index, updateBid, setModal }) => {

  const {
    bidId,
    amount,
    bidStatus,
    projectSummary: { projectName, projectStart },
  } = bid || {};

  const handleConfirm = () => {
    setModal({
      title: "Confirm project",
      message: "Are you sure you want to accept this project?",
      confirmButton: "Accept",
      rejectButton: "Reject",
      onConfirm: () =>
        updateBid(bidId, {
          amount: bid.amount,
          status: BidStatus.CONFIRMED,
        }),

      onReject: () =>
        updateBid(bidId, {
          amount: bid.amount,
          status: BidStatus.CANCELED,
        }),
    });
  };

  return (
    <>
      <tr>
        <td>{index + 1}</td>
        <td className="underline">
          <p>{projectName}</p>
        </td>
        <td className="flex justify-center">${amount}</td>
        <td>
          <div className="text-cyan-500 font-bold bg-cyan-400/15 p-2 rounded-xl w-fit">
            {bidStatus}
          </div>
        </td>
        <td>{projectStart}</td>
        <td className="flex gap-2 items-center justify-end">
          <MdEdit />
          <MdDeleteOutline />
          <div>
            {bidStatus === "PENDING" && (
              <button
                className={`btn btn-primary text-cyan-500 font-bold bg-cyan-400/15 p-2 rounded-xl w-fit border-none outline-none`}
                onClick={handleConfirm}
              >
                CONFIRM
              </button>
            )}
          </div>
        </td>
      </tr>
    </>
  );
};

export default FreelancerBidRow;
