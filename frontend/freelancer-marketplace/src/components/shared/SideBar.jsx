import { Link } from "react-router";
import { MdBusinessCenter } from "react-icons/md";
import { IoSettingsOutline } from "react-icons/io5";

const SideBar = ({ children, role }) => {
  const userProfile =
    role === "client" ? "Client" : role === "seller" ? "Freelancer" : "Admin";

  return (
    <div className="drawer lg:drawer-open">
      <input id="my-drawer" type="checkbox" className="drawer-toggle" />
      <div className="drawer-content text-white bg-slate-800 p-8">
        {children}
      </div>
      <div className="drawer-side bg-slate-700">
        <div className="pt-8">
          <Link to="/">
            <label className="drawer-overlay font-bold text-2xl p-8 bg-slate-800 z-10 text-white">
              [logo]FreelanderMarketplace
            </label>
          </Link>
        </div>

        <div className="flex flex-col gap-4 mt-10 p-6 text-gray-300 text-start">
          <p className="font-bold">DASHBOARDS</p>
          <div className="flex items-center gap-4 text-2xl">
            <MdBusinessCenter />
            <Link to={`/${userProfile}`}>{userProfile} Dashboard</Link>
          </div>

          <p className="font-bold mt-5">ACCOUNT</p>
          <div className="flex items-center gap-4 text-2xl">
            <IoSettingsOutline />
            <Link to={`/${userProfile}`}>Profile Settings</Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SideBar;
