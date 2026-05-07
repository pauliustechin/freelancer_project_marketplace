import { useParams } from "react-router";
import useProjectsStore from "../../../store/projectsStore";
import PlaceBidModal from "./PlaceBidModal";
import { useState } from "react";
import useUsersStore from "../../../store/usersStore";
import { useNavigate } from "react-router";
import { FaClockRotateLeft } from "react-icons/fa6";
import { FaPeopleGroup } from "react-icons/fa6";

const ProjectInfo = () => {
  const { projectId } = useParams("id");
  const { projects } = useProjectsStore();
  const project = projects.find((pr) => pr.projectId === Number(projectId));
  const { projectName, description } = project;
  const [open, setOpen] = useState(false);
  const { user } = useUsersStore((state) => state);
  const navigate = useNavigate();

  const handleOpen = () => {
    if (!user.userId) {
      navigate("/login");
    }
    setOpen(true);
  };

  return (
    <aside className="h-full w-[40%] p-8">
      <div
        className="text-start text-2xl font-bold"
        onClick={handleOpen}
        className={`card h-full border border-gray-300 bg-white text-start p-4 flex flex-col gap-4`}
      >
        <div className="w-fit p-2 bg-gray-200 rounded-lg">Development</div>
        <p className="text-2xl font-bold">{projectName}</p>
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
        <div className="flex gap-2">
          <div className="w-[50%] h-fit rounded-2xl p-4 bg-gray-100 border border-gray-100">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-sm font-medium text-gray-500">Budget</p>
                <h2 className="mt-2 text-2xl font-bold text-gray-900">
                  $5k - $10k
                </h2>
              </div>
            </div>
          </div>
          <div className="w-[50%] h-fit rounded-2xl p-4 bg-gray-100 border border-gray-100">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-sm font-medium text-gray-500">Start date</p>
                <h2 className="mt-2 text-2xl font-bold text-gray-900">
                  July, 2026
                </h2>
              </div>
            </div>
          </div>
        </div>
        <pc className="font-bold">Project Description</pc>
        <p>{description}</p>
        <hr className="border-gray-200" />
        <div>
          <p className="mb-2 text-gray-400 text-sm font-bold">
            CURRENT ACTIVITY
          </p>
          <div className="flex font-bold">
            <FaPeopleGroup />
            <p>15 bids</p>
          </div>
        </div>
        <button
          className="mt-auto btn btn-primary w-36 self-end bg-slate-700 text-white"
          onClick={handleOpen}
        >
          Place a bid
        </button>
      </div>
      <PlaceBidModal open={open} setOpen={setOpen}></PlaceBidModal>
    </aside>
  );
};

export default ProjectInfo;
