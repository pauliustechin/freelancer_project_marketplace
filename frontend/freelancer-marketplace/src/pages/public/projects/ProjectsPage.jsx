import useProjectsStore from "../../../store/projectsStore";
import { Outlet, useLocation } from "react-router";
import MyPagination from "../../../components/shared/MyPagination";
import ProjectCard from "./ProjectCard";
import { useEffect } from "react";
import Header from "../../../components/shared/Header";
import Footer from "../../../components/shared/Footer";
import ProjectFilter from "./ProjectFilter";

const ProjectsPage = () => {
  const { projects, fetchProjects } = useProjectsStore((state) => state);
  const location = useLocation();
  const currentProjectId = Number(location.pathname.split("/").pop());
  const project = projects.find((pr) => pr.projectId === currentProjectId);

  useEffect(() => {
    fetchProjects(location.search);
  }, [location.search, fetchProjects]);

  return (
    <>
      <Header />
      <main className="flex items-start p-8 gap-8">
        <div className="w-[60%]">
          <ProjectFilter currentProjectId={currentProjectId}></ProjectFilter>
          <div className="flex flex-col gap-4 justify-between w-full">
            {projects?.map((project) => (
              <ProjectCard
                key={project.projectId}
                project={project}
                styling={`width-full ${currentProjectId === project.projectId && "outline-3 outline-cyan-600"}`}
              ></ProjectCard>
            ))}
          </div>
          <MyPagination></MyPagination>
        </div>
        {project && <Outlet context={project}></Outlet>}
      </main>
      <Footer />
    </>
  );
};

export default ProjectsPage;
