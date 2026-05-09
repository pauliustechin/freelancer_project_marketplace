import useProjectsStore from "../../../store/projectsStore";
import { Outlet, useLocation } from "react-router";
import ProjectPagination from "./ProjectPagination";
import ProjectCard from "./ProjectCard";
import { useEffect, useState } from "react";
import Header from "../../../components/shared/Header";
import Footer from "../../../components/shared/Footer";
import ProjectFilter from "./ProjectFilter";

const ProjectsPage = () => {
  const { projects, fetchProjects } = useProjectsStore((state) => state);
  const location = useLocation();
  const currentProjectId = Number(location.pathname.split("/").pop());
  const project = projects.find((pr) => pr.projectId === currentProjectId);
  const [ page, setPage ] = useState(null);

  useEffect(() => {
    fetchProjects(location.search);
    console.log("fetching projects")
  }, [location.search, fetchProjects]);

  return (
    <>
      <Header />
      <main className="flex items-start p-8 gap-8">
        <div className="w-[60%] flex flex-col gap-4 items-center">
          <ProjectFilter setPage={setPage}></ProjectFilter>
          <div className="flex flex-col gap-4 justify-between w-full">
            {projects?.map((project) => (
              <ProjectCard
                key={project.projectId}
                project={project}
                styling={`width-full ${currentProjectId === project.projectId && "outline-3 outline-cyan-600"}`}
              ></ProjectCard>
            ))}
          </div>
          <ProjectPagination page={page} setPage={setPage}></ProjectPagination>
        </div>
        {project && <Outlet context={project}></Outlet>}
      </main>
      <Footer />
    </>
  );
};

export default ProjectsPage;
