import { useNavigate } from "react-router";

const ClientProjectRow = ({ project, index }) => {

  const { projectName, description } = project;
  const navigate = useNavigate();

  const handleOpen = () => {
    navigate(`/client/projects/${project.projectId}`)
  };

  return (
    <>
      <tr>
        <th>{index}</th>
        <td onClick={handleOpen} className="underline">{projectName}</td>
        <td>{description}</td>
        <td>0</td>
      </tr>
    </>
  );
};

export default ClientProjectRow;
