import useProjectsStore from "../../../store/projectsStore";
import { MdEdit } from "react-icons/md";
import { MdDeleteOutline } from "react-icons/md";

const ProjectBidRow = ({ bid, index, register, projectId }) => {

  const { clientProjects } = useProjectsStore(state => state);
  const { 
    bidId,
    bidStatus,
    amount,
    freelancer: {firstName, lastName, email}
  } = bid;

  const project = clientProjects.find(pr => pr.projectId === Number(projectId));
  const isDisabled = project?.projectStatus !== "OPEN" || bidStatus === "CANCELED";

  return (
      <tr>
        <th>{index + 1}</th>
        <th>{firstName + " " + lastName}</th>
        <td>{email}</td>
        <td>{bidStatus}</td>
        <td>{amount}</td>
        <td>
          <input
            type="radio"
            value={bidId}
            disabled={isDisabled}
            {...register("selectedBid")}
          />
        </td>
        <td><MdEdit /></td>
        <td><MdDeleteOutline /></td>
      </tr>
  );
};

export default ProjectBidRow;
