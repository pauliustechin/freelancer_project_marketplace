import { useNavigate } from "react-router";
import { MdEdit } from "react-icons/md";
import { MdDeleteOutline } from "react-icons/md";

const ClientProjectRow = ({ project, index }) => {
  const { projectName, description, projectStatus } = project;
  const navigate = useNavigate();

  const handleOpen = () => {
    navigate(`/client/projects/${project.projectId}`);
  };

  return (
    <>
      <tr>
        <th>{index}</th>
        <td onClick={handleOpen} className="underline">
          <p>{projectName}</p>
          <p>
            {description.length > 20
              ? description.slice(0, 20) + "..."
              : description}
          </p>
        </td>
        <td className="flex justify-center">
          <div className="text-cyan-600 font-bold bg-cyan-400/20 p-2 rounded-xl w-fit">
            {projectStatus}
          </div>
        </td>
        <td>$3000 - $5000</td>
        <td>2026-07-01</td>
        <td className="text-center">
          <p className="bg-gray-300/20 rounded-4xl max-fit p-1">12</p>
        </td>
        <td className="flex gap-2 items-center">
          <MdEdit />
          <MdDeleteOutline />
          <div className="text-cyan-600 font-bold bg-cyan-400/20 p-2 rounded-xl w-fit">
            {projectStatus}
          </div>
        </td>
      </tr>
    </>
  );
};

export default ClientProjectRow;
