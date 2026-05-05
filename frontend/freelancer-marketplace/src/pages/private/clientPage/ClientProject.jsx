import { useNavigate } from "react-router";

const ClientProject = ({ project, index }) => {
  const { projectName, description } = project;
  const navigate = useNavigate();

  const handleOpen = () => {
    navigate(`/client/projects/${project.projectId}`)
  };

  return (
    <>
      <tr>
        <th>{index}</th>
        <td onClick={handleOpen}>{projectName}</td>
        <td>{description}</td>
        <td>0</td>
      </tr>
    </>
  );
};

export default ClientProject;
