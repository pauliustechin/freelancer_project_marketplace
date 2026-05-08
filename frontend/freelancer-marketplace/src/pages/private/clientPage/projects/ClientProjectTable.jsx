import ClientProject from "./ClientProjectRow";
import useProjectsStore from "../../../../store/projectsStore";
import useUsersStore from "../../../../store/usersStore";
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
      <div className="flex justify-between">
        <h1 className="text-start text-2xl font-bold p-2">Projects</h1>
        <div>
          <button className="btn btn-primary text-lg bg-cyan-600 font-bold border-none">
            + Create Project
          </button>
        </div>
      </div>

      <table className="table bg-slate-700">
        <thead className="text-gray-400">
          <tr>
            <th></th>
            <th>PROJECT NAME</th>
            <th className="text-center">STATUS</th>
            <th>BUDGET</th>
            <th>START DATE</th>
            <th className="text-center">BIDS</th>
            <th className="text-end">ACTIONS</th>
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
