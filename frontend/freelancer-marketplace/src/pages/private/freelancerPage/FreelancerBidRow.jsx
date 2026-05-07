import { useNavigate } from "react-router";

const FreelancerBidRow = ({ bid, index, setMessage, setBid, setOpen }) => {

  const {
    amount,
    bidStatus,
    projectSummary: { projectId, projectName, projectStart }
  } = bid;

  const navigate = useNavigate();

  const handleOpen = () => {
      setMessage(
        "Are you sure you want to confirm your bid? Once confirmed, this action cannot be undone.",
      );
      setBid(bid);
      setOpen(true);
    };


  return (
    <>
      <tr>
        <th>{index + 1}</th>
        <td onClick={() => navigate(`/projects/${projectId}`)} className="underline">{projectName}</td>
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
    </>
  );
};

export default FreelancerBidRow;
