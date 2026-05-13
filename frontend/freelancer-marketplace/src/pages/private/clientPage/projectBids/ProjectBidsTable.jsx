import { useEffect, useState } from "react";
import useBidsStore from "../../../../store/bidsStore";
import ClientProjectBid from "./ProjectBidRow";
import { useParams, useNavigate } from "react-router";
import { useForm } from "react-hook-form";
import ConfirmationModal from "../../../../components/shared/ConfirmationModal";
import { BidStatus } from "../../../../enums/bidStatus";

const ProjectBidsTable = () => {

  const { projectId } = useParams();
  const navigate = useNavigate();
  const { register, watch, handleSubmit } = useForm();
  const selectedBid = watch("selectedBid");
  const [modal, setModal] = useState(null);

  const { fetchBidsByProject, projectBids, acceptBid } = useBidsStore(
    (state) => state,
  );

  const handleConfirm = () => {

    const bid = projectBids.find((b) => b.bidId === Number(selectedBid));

    const { bidId, amount, freelancer: { firstName }} = bid || {};

    setModal({
      title: "Confirm project",
      message: `Are you sure you want to accept bid from ${firstName + ", amount: " + amount} $`,
      confirmButton: "Accept",
      onConfirm: () =>
      acceptBid(bidId, navigate, {
        status: BidStatus.ACCEPTED,
      }),
    });
  };

  useEffect(() => {
    fetchBidsByProject(projectId);
  }, [fetchBidsByProject, projectId]);

  return (
    <div className="overflow-x-auto text-slate-200 bg-slate-400 min-h-screen p-8">
      <h1 className="text-start text-2xl font-bold p-2">Project bids</h1>
      <form onSubmit={handleSubmit(handleConfirm)} className="flex flex-col">
        <div className="modal-action flex flex-col">
          <div className="overflow-x-auto">
            <table className="table bg-slate-700">
              <thead className="text-gray-400">
                <tr>
                  <th></th>
                  <th>FEELANCER</th>
                  <th className="text-center">STATUS</th>
                  <th>BID AMOUNT</th>
                  <th>EMAIL</th>
                  <th>DATE</th>
                  <th className="text-end">ACCEPT BID</th>
                </tr>
              </thead>
              <tbody>
                {projectBids.map((bid, index) => {
                  return (
                    <ClientProjectBid
                      key={bid.bidId}
                      bid={bid}
                      index={index}
                      register={register}
                      projectId={projectId}
                    />
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
        <button
          type="submit"
          className={`btn btn-primary w-28 bg-cyan-600 font-bold text-white m-4 self-end border-none 
          ${!selectedBid ? "invisible" : ""}`}
        >
          ACCEPT
        </button>
      </form>
      <ConfirmationModal
        modal={modal}
        setModal={setModal}
      ></ConfirmationModal>
    </div>
  );
};

export default ProjectBidsTable;
