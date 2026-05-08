import SideBar from "../../../components/shared/SideBar";
import { Outlet } from "react-router";
import ProfileDropdown from "../../../components/shared/ProfileDropdown";

const ClientPage = () => {

  return (
    <main className="flex">
      <SideBar role="client"></SideBar>
      <div className="flex flex-col w-full">
        <div className="flex justify-between items-center h-22 p-8 bg-slate-700 text-slate-200">
          <div className="text-start">
            <h1 className="text-2xl font-bold">Client panel</h1>
            <p>Manage your projects and review freelancer bids.</p>
          </div>
          <div className="text-black">
            <ProfileDropdown
              theme={"bg-slate-300 text-slate-800"}
            ></ProfileDropdown>
          </div>
        </div>
        <Outlet></Outlet>
      </div>
    </main>
  );
};

export default ClientPage;
