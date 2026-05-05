import { useNavigate } from "react-router";

const ProjectCard = ({ project }) => {

  const { projectId, projectName } = project;
  const navigate = useNavigate();

  const handleOpen = () => {
    navigate(`/projects/${projectId}`)
  };

  return (
    <div className="card w-72 h-full">
      <figure className="mt-4 rounded-sm">
        <img src="https://placehold.co/400x400" alt="project image" />
      </figure>
      <p className="text-start p-2 text-2xl" onClick={handleOpen}>
        {projectName}
      </p>
    </div>
  );
};

export default ProjectCard;
