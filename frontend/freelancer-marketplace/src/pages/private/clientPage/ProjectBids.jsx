import { useEffect, useState } from "react";
import useBidsStore from "../../../store/bidsStore";
import ClientProjectBid from "./ProjectBidRow";
import { useParams, useNavigate } from "react-router";
import { useForm } from "react-hook-form";
import ConfirmationModal from "../../../components/shared/ConfirmationModal";

const ProjectBids = () => {
  const { projectId } = useParams();
  const navigate = useNavigate();
  const { fetchBidsByProject, projectBids, acceptBid } = useBidsStore((state) => state);
  const { register, watch, handleSubmit } = useForm();
  const selectedBid = watch("selectedBid");
  const [ open, setOpen ] = useState(false);
  const [ message, setMessage ] = useState("");
  const [ status, setStatus ] = useState(false);

  const onSubmit = () => {
    const bid = projectBids.find(b => b.bidId === Number(selectedBid));
    if (!bid) return;
    setMessage(`Are you sure you want to accept bid from ${bid.freelancer.firstName + ", amount: " + bid.amount} $`)
    setOpen(true);
  };

  useEffect(() => {
    if (status) {
      acceptBid(selectedBid, navigate, {
        status: "ACCEPTED"
      });
    }
  }, [status]);

  useEffect(() => {
    fetchBidsByProject(projectId);
  }, [fetchBidsByProject, projectId]);

  return (
    <>
      <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col">
        <div className="modal-action flex flex-col">
          <div className="overflow-x-auto">
            <table className="table">
              <thead>
                <tr>
                  <th></th>
                  <th>Freelancer</th>
                  <th>Email</th>
                  <th>Status</th>
                  <th>Amount</th>
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
                    />
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
        <button
          type="submit"
          className={`btn btn-primary w-28 bg-green-400 font-bold text-white m-4 self-end
          ${!selectedBid ? "invisible" : ""}`}
        >
          ACCEPT
        </button>
      </form>
      <ConfirmationModal open={open} setOpen={setOpen} setStatus={setStatus} message={message}></ConfirmationModal>
    </>
  );
};

export default ProjectBids;
