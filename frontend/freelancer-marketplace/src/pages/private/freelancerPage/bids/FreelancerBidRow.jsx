import { MdEdit } from "react-icons/md";
import { MdDeleteOutline } from "react-icons/md";
import { BidStatus } from "../../../../enums/bidStatus";
import useBidsStore from "../../../../store/bidsStore";

const FreelancerBidRow = ({
  bid,
  index,
  setConfirmModal,
  setEditModal,
}) => {

  const {
    bidId,
    amount,
    bidStatus,
    projectSummary: { projectName, projectStart },
  } = bid || {};

  const { updateBid, deleteBid } = useBidsStore((state) => state);

  const handleConfirm = () => {
    setConfirmModal({
      title: "Confirm project",
      message: "Are you sure you want to confirm this project?",
      confirmButton: "Accept",
      rejectButton: "Reject",
      onConfirm: () =>
        updateBid(bidId, {
          amount: amount,
          status: BidStatus.CONFIRMED,
        }),

      onReject: () =>
        updateBid(bidId, {
          amount: amount,
          status: BidStatus.CANCELED,
        }),
    });
  };

  const handleEdit = () => {
    setEditModal({
      title: "Edit bid",
      message: "Update bid amount",
      confirmButton: "Edit",
      bid: bid,
      onConfirm: () =>
        deleteBid(bidId, {
          amount: amount,
          status: BidStatus.OPEN,
        }),
    });
  };

  const handleDelete = () => {
    setConfirmModal({
      title: "Delete bid",
      message: "Are you sure you want to delete a bid? ",
      confirmButton: "Delete",
      onConfirm: () =>
        deleteBid(bidId, {
          amount: amount,
          status: BidStatus.OPEN,
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
          <MdEdit onClick={handleEdit} />
          <MdDeleteOutline onClick={handleDelete}/>
          <div>
            {bidStatus === BidStatus.PENDING && (
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
