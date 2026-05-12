import useProjectsStore from "../../../../store/projectsStore";
import useUsersStore from "../../../../store/usersStore";
import { useNavigate } from "react-router";
import ProjectForm from "./ProjectForm";

const CreateProject = () => {

  const { createProject } = useProjectsStore(state => state);
  const { user } = useUsersStore(state => state);
  const navigate = useNavigate();

  const handleCreate = (formData) => {
    createProject(formData, user.userId, navigate);
  }

  return (
    <main className="text-slate-200 bg-slate-400 min-h-screen p-8">
      <h1 className="text-2xl font-bold text-start mb-2">Create New Project</h1>
      <ProjectForm onSubmit={handleCreate}></ProjectForm>
    </main>
  );
};

export default CreateProject;
