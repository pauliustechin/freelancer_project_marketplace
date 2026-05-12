import useProjectsStore from "../../../../store/projectsStore";
import { useNavigate } from "react-router";
import ProjectForm from "./ProjectForm";
import { useParams } from "react-router";

const EditProject = () => {

  const { editProject, clientProjects } = useProjectsStore(state => state);
  const navigate = useNavigate();
  const { projectId } = useParams();
  const project = clientProjects.find(pr => pr.projectId === Number(projectId));

  const handleEdit = (formData) => {
    editProject(formData, projectId, navigate);
  }

  return (
    <main className="text-slate-200 bg-slate-400 min-h-screen p-8">
      <h1 className="text-2xl font-bold text-start mb-2">Edit project</h1>
      <ProjectForm onSubmit={handleEdit} defaultValues={project}></ProjectForm>
    </main>
  );
};

export default EditProject;
