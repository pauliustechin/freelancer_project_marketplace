import ClientProjectTable from "./ClientProjectTable";
import SideBar from "../../../components/shared/SideBar";
import { useLocation, Outlet } from "react-router";

const ClientPage = () => {
  const location = useLocation();
  const isProjectBidTable =
    location.pathname.substring(0, location.pathname.lastIndexOf("/")) ===
    "/client/projects";

  return (
    <SideBar role="client">
      <main>
        <div className="flex justify-between mb-6 items-center">
          <div className="text-start">
            <h1 className="text-2xl font-bold">Client Dashboard</h1>
            <p>Manage your active projects and review freelancer bids.</p>
          </div>
          <hr />
          <div>
            <button className="btn btn-primary text-lg bg-cyan-600 font-bold border-none">
              + Create Project
            </button>
          </div>
        </div>
        {isProjectBidTable ? (
          <Outlet></Outlet>
        ) : (
          <ClientProjectTable></ClientProjectTable>
        )}
      </main>
    </SideBar>
  );
};

export default ClientPage;
