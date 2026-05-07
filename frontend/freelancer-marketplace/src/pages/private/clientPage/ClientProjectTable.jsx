import ClientProject from "./ClientProjectRow";
import useProjectsStore from "../../../store/projectsStore";
import useUsersStore from "../../../store/usersStore";
import { useEffect } from "react";



const ClientProjectTable = () => {
  const { user } = useUsersStore((state) => state);
  const { fetchClientProjects, clientProjects } = useProjectsStore(
    (state) => state,
  );

  useEffect(() => {
    fetchClientProjects(user.userId);
  }, [fetchClientProjects, user.userId]);

  return (
    <div className="overflow-x-auto">
      <table className="table bg-slate-700">
        <thead className="text-gray-400">
          <tr>
            <th></th>
            <th>PROJECT NAME</th>
            <th className="text-center">STATUS</th>
            <th>BUDGET</th>
            <th>START DATE</th>
            <th className="text-center">BIDS</th>
            <th>ACTIONS</th>
          </tr>
        </thead>
        <tbody>
          {clientProjects.map((project, index) => (
            <ClientProject
              key={project.projectId}
              project={project}
              index={index + 1}
            ></ClientProject>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ClientProjectTable;
