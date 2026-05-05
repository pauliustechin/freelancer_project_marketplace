import { useParams } from "react-router";
import useProjectsStore from "../../../store/projectsStore";
import PlaceBidModal from "./PlaceBidModal";
import { useState } from "react";
import useUsersStore from "../../../store/usersStore";
import { useNavigate } from "react-router";

const ProjectInfo = () => {
  const { projectId } = useParams("id");
  const { projects } = useProjectsStore();
  const project = projects.find((pr) => pr.projectId === Number(projectId));
  const { projectName, description } = project;
  const [open, setOpen] = useState(false);
  const { user } = useUsersStore(state => state);
  const navigate = useNavigate();

  const handleOpen = () => {
    if(!user.userId) {
      navigate("/login")
    }
    setOpen(true);
  };

  return (
    <main className="h-full w-full p-8">
      <div className="bg-slate-50 w-full h-full rounded shadow-sm p-8 font-bold text-start flex flex-col">
        <h1 className="text-slate-700 mb-4 text-2xl">{projectName}</h1>
        <hr />
        <p className="mt-4">{description}</p>
        <button 
          className="mt-auto btn btn-primary w-36 self-end bg-slate-700 text-white"
          onClick={handleOpen}
        >Place a bid</button>
      </div>
      <PlaceBidModal open={open} setOpen={setOpen} ></PlaceBidModal>
    </main>
  );
};

export default ProjectInfo;
