import ClientProject from "./ClientProject";
import useProjectsStore from "../../../store/projectsStore";
import useUsersStore from "../../../store/usersStore";
import { useEffect } from "react";

const ClientPage = () => {

  const { user } = useUsersStore(state => state);
  const { fetchClientProjects } = useProjectsStore(state => state);
  const { clientProjects } = useProjectsStore(state => state)

  useEffect(() => {
    fetchClientProjects(user.userId);
  }, [fetchClientProjects])

  console.log(clientProjects)


  return (
    <div>ClientPage
      <div className="overflow-x-auto">
      <table className="table">
        {/* head */}
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