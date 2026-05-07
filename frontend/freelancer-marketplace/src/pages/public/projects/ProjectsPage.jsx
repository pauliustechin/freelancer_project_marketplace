import useProjectsStore from "../../../store/projectsStore";
import { Link, Outlet } from "react-router";
import MyPagination from "../../../components/shared/MyPagination";
import MyFiltering from "../../../components/shared/MyFiltering";
import ProjectCard from "./ProjectCard";
import ProjectInfo from "./ProjectInfo";
import { useState } from "react";
import { useParams } from "react-router";
import Header from "../../../components/shared/Header";
import Footer from "../../../components/shared/Footer";

const ProjectsPage = () => {
  const { projects } = useProjectsStore((state) => state);
  const [currentProjectId, setCurrentProjectId] = useState(null);

  // const handleClick = (id) => {
  //   setCurrentProject(id);
  //   console.log(currentProject)
  // }

  return (
    <>
      <Header></Header>
      <main className="flexitems-center p-8 gap-8 flex">
        <div className="w-[60%]">
          <MyFiltering></MyFiltering>
          <div className="flex flex-col gap-4 justify-between w-full">
            {projects.slice(0, 3).map((project) => (
              <ProjectCard
                key={project.projectId}
                project={project}
                styling={`width-full ${currentProjectId === project.projectId && "outline-3 outline-slate-600"}`}
                setCurrentProjectId={setCurrentProjectId}
              ></ProjectCard>
            ))}
          </div>
          <MyPagination></MyPagination>
        </div>
        <Outlet></Outlet>
      </main>
      <Footer></Footer>
    </>
  );
};

export default ProjectsPage;
