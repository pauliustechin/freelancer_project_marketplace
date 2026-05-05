// import { useForm } from "react-hook-form";
// import InputField from "../../../components/shared/InputField";
import { useEffect } from "react";
import useBidsStore from "../../../store/bidsStore";
import useUsersStore from "../../../store/usersStore";

const PlaceBidModal = () => {
  const { user } = useUsersStore();
  const { fetchBidsByProject } = useBidsStore();
  const { projectBids } = useBidsStore((state) => state);

  useEffect(() => {
    fetchBidsByProject(user.userId);
  }, [fetchBidsByProject]);

  return (
    <>
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
                        <tr key={bid.bidId}>
                          <th>{index + 1}</th>
                          <th>{bid.freelancer.firstName + " " + bid.freelancer.lastName}</th>
                          <td>{bid.freelancer.email}</td>
                          <td>{bid.bidStatus}</td>
                          <td>{bid.amount}</td>
                        </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
          </div>
    </>
  );
};

export default PlaceBidModal;
