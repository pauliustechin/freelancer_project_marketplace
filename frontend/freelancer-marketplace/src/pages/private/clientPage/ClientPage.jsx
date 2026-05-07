import ClientProjectTable from "./ClientProjectTable";
import SideBar from "../../../components/shared/SideBar";
import { useLocation, Outlet } from "react-router";


const ClientPage = () => {
const location = useLocation();
// console.log(location.substring(0, location.pathname.lastIndexOf("/"))) 
const isProjectBidTable = location.pathname.substring(0, location.pathname.lastIndexOf("/")) === "/client/projects";

  return (
    <SideBar>
      <main>
        {isProjectBidTable ? <Outlet></Outlet> : <ClientProjectTable></ClientProjectTable>}  
      </main>
    </SideBar>
  );
};

export default ClientPage;
