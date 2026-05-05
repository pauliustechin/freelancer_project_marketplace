import useProjectsStore from "../../store/projectsStore";
import ProjectCard from "./projects/ProjectCard";

const HomePage = () => {
  const { projects } = useProjectsStore((state) => state);

  return (
    <main className="flex flex-col">
      <p>HOME PAGE</p>
      <section className="flex flex-col gap-2 p-4">
        <div className="flex justify-between">
          <p className="text-2xl font-bold text-start text-slate-700">
            OPEN PROJECTS
          </p>
          <p className="text-lg font-bold text-end text-slate-700 underline">
            All projects
          </p>
        </div>

        <div className="flex h-80 gap-4 justify-between">
          {projects.slice(0, 3).map((project) => (
            <ProjectCard
              key={project.projectId}
              project={project}
            ></ProjectCard>
          ))}
        </div>
      </section>
    </main>
  );
};

export default HomePage;
