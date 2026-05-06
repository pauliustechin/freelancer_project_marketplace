import ClientProject from "./ClientProjectRow";
import useProjectsStore from "../../../store/projectsStore";
import useUsersStore from "../../../store/usersStore";
import { useEffect } from "react";

const ClientPage = () => {

  const { user } = useUsersStore(state => state);
  const { fetchClientProjects, clientProjects } = useProjectsStore(state => state);

  useEffect(() => {
    fetchClientProjects(user.userId);
  }, [fetchClientProjects, user.userId])

  return (
    <div>ClientPage
      <div className="overflow-x-auto">
      <table className="table">
        <thead>
          <tr>
            <th></th>
            <th>Project Name</th>
            <th>Description</th>
            <th>Bids</th>
          </tr>
        </thead>
        <tbody>
        {clientProjects.map((project, index) => <ClientProject key={project.projectId} project={project} index={index + 1}></ClientProject>)}
        </tbody>
      </table>
    </div>
    </div>
  )
}

export default ClientPage;