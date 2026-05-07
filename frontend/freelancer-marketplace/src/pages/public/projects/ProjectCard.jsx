import { useNavigate } from "react-router";
import { FaClockRotateLeft } from "react-icons/fa6";
import { FaPeopleGroup } from "react-icons/fa6";


const ProjectCard = ({ project }) => {
  const { projectId, projectName, description } = project;
  const navigate = useNavigate();

  const handleOpen = () => {
    navigate(`/projects/${projectId}`);
  };

  return (
    <div className="card w-72 h-full border border-gray-300 text-start p-4 flex flex-col gap-4">
      <div className="w-fit p-2 bg-gray-200 rounded-lg">Development</div>
      <p className="text-start text-2xl font-bold" onClick={handleOpen}>
        {projectName}
      </p>
      <p>{description}</p>
      <div className="flex gap-4">
        <div className="w-fit p-2 text-gray-400 rounded-lg border border-gray-200">
          React
        </div>
        <div className="w-fit p-2 text-gray-400 rounded-lg border border-gray-200">
          Spring
        </div>
        <div className="w-fit p-2 text-gray-400 rounded-lg border border-gray-200">
          Tailwind
        </div>
      </div>
      <hr className="border-gray-200" />
      <div className="flex justify-between">
        <div className="text-gray-400 flex gap-2">
          <FaClockRotateLeft />
          <p> Posted 2h ago</p>
        </div>
        <div className="flex font-bold">
          <FaPeopleGroup />
          <p>15 bids</p>
        </div>
      </div>

    </div>
  );
};

export default ProjectCard;
