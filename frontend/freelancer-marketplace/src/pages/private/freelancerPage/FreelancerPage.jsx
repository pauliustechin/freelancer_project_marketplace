// import FreelancerBidTable from "./FreelancerBidTable";
import SideBar from "../../../components/shared/SideBar";
import ProfileDropdown from "../../../components/shared/ProfileDropdown";
import { Outlet } from "react-router";

const FreelancerPage = () => {
  return (
    <main className="flex">
      <SideBar role="freelancer"></SideBar>
      <div className="flex flex-col w-full">
        <div className="flex justify-between items-center h-22 p-8 bg-slate-700 text-slate-200">
          <div className="text-start">
            <h1 className="text-2xl font-bold">Freelancer panel</h1>
            <p>Manage your active bids.</p>
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

    // <SideBar role={"seller"}>
    //   <div className="flex justify-between mb-6 items-center">
    //     <div className="text-start">
    //       <h1 className="text-2xl font-bold">Freelancer dashboard</h1>
    //       <p>Manage your active bids.</p>
    //     </div>
    //     <div className="text-black">
    //       <ProfileDropdown
    //         theme={"bg-slate-300 text-slate-800"}
    //       ></ProfileDropdown>
    //     </div>
    //   </div>
    //   <hr className="text-slate-300/50"/>
    //   <div>
    //     <FreelancerBidTable></FreelancerBidTable>
    //   </div>
    // </SideBar>
  );
};

export default FreelancerPage;
