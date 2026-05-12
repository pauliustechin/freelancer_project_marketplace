import { useNavigate, Link } from "react-router";
import { MdEdit } from "react-icons/md";
import { MdDeleteOutline } from "react-icons/md";

const ClientProjectRow = ({ project, index }) => {
  const { projectId, projectName, description, projectStatus } = project;
  const navigate = useNavigate();

  const handleOpen = () => {
    navigate(`/client/projects/${projectId}`);
  };

  return (
    <>
      <tr>
        <th>{index}</th>
        <td>
          <p className="font-bold">{projectName}</p>
          <p className="text-sm text-gray-400">
            {description.length > 20
              ? description.slice(0, 20) + "..."
              : description}
          </p>
        </td>
        <td className="flex justify-center">
          <div className="text-cyan-500 font-bold bg-cyan-400/15 p-2 rounded-xl w-fit">
            {projectStatus}
          </div>
        </td>
        <td>$3000 - $5000</td>
        <td>2026-07-01</td>
        <td className="text-center">
          <p className="bg-gray-300/20 rounded-4xl max-fit p-1">12</p>
        </td>
        <td className="flex gap-2 items-center justify-end">
          <Link to={`/client/edit-project/${projectId}`} >
            <MdEdit />
          </Link>

          <MdDeleteOutline />
          <button
            className="btn btn-primary text-cyan-500 font-bold bg-cyan-400/15 p-2 rounded-xl w-fit border-none outline-none"
            onClick={handleOpen}
          >
            View bids
          </button>
        </td>
      </tr>
    </>
  );
};

export default ClientProjectRow;
