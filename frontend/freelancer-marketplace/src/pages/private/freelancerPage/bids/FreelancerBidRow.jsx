import { MdEdit } from "react-icons/md";
import { MdDeleteOutline } from "react-icons/md";

const FreelancerBidRow = ({ bid, index, setMessage, setBid, setOpen }) => {
  const {
    amount,
    bidStatus,
    projectSummary: { projectName, projectStart },
  } = bid;


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
        <td>{index + 1}</td>
        <td className="underline">
          <p>{projectName}</p>
        </td>
        <td className="flex justify-center">${amount}</td>
        <td>
          {" "}
          <div className="text-cyan-500 font-bold bg-cyan-400/15 p-2 rounded-xl w-fit">
            {bidStatus}
          </div>
        </td>
        <td>{projectStart}</td>
        <td className="flex gap-2 items-center justify-end">
          <MdEdit />
          <MdDeleteOutline />
          <td>
            {bidStatus === "PENDING" && (
              <button
                className={`btn btn-primary text-cyan-500 font-bold bg-cyan-400/15 p-2 rounded-xl w-fit border-none outline-none`}
                onClick={handleOpen}
              >
                CONFIRM
              </button>
            )}
          </td>
        </td>
      </tr>
    </>
  );
};

export default FreelancerBidRow;
