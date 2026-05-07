import { Link } from "react-router";
import { MdBusinessCenter } from "react-icons/md";
import { IoSettingsOutline } from "react-icons/io5";



const SideBar = ({ children }) => {
  return (
    <div className="drawer lg:drawer-open">
      <input id="my-drawer" type="checkbox" className="drawer-toggle" />
      <div className="drawer-content text-white bg-slate-800 p-8">
        <div className="flex justify-between mb-6 items-center">
          <div className="text-start">
            <h1 className="text-2xl font-bold">Client Dashboard</h1>
            <p>Manage your active projects and review freelancer bids.</p>
          </div>
          <div>
            <button className="btn btn-primary text-lg bg-cyan-600 font-bold border-none">+ Create Project</button>
          </div>
        </div>
        {children}
      </div>
      <div className="drawer-side bg-slate-700">
        <label className="drawer-overlay font-bold text-2xl p-4 bg-slate-800 z-10 text-white">
          [logo]FreelanderMarketplace
        </label>
        <div className="flex flex-col gap-4 mt-10 p-4 text-gray-300 text-start">
          <p className="font-bold">DASHBOARDS</p>
          <div className="flex items-center gap-4 text-2xl">
            <MdBusinessCenter />
            <Link to="/client">Client Dashboard</Link>
          </div>

          <p className="font-bold mt-5">ACCOUNT</p>
          <div className="flex items-center gap-4 text-2xl">
            <IoSettingsOutline />
            <Link to="/client">Profile Settings</Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SideBar;
