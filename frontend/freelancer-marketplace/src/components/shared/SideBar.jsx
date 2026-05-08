import { Link, NavLink } from "react-router";
import { IoSettingsOutline } from "react-icons/io5";
import {
  clientNavigation,
  freelancerNavigation,
  adminNavigation,
} from "../../utils/userNavigation";

const SideBar = ({ role }) => {
  const sideBarLayout =
    role === "client"
      ? clientNavigation
      : role === "freelancer"
        ? freelancerNavigation
        : role === "admin"
          ? adminNavigation
          : null;

  return (
    <aside className="drawer lg:drawer-open w-fit bg-slate-700">
      <input id="my-drawer" type="checkbox" className="drawer-toggle" />
      <div className="drawer-side ">
        <div className="h-22 bg-slate-800 z-10 flex justify-center items-center p-4">
          <Link to="/">
            <p className="drawer-overlay font-bold text-2xl p-2 text-white cursor-pointer">
              [logo]FreelancerMarketplace
            </p>
          </Link>
        </div>

        <div className="flex flex-col gap-4 mt-5 p-6 text-gray-300 text-start">
          <p className="font-bold">PROFILE</p>
          <nav>
            <ul className="flex flex-col gap-4 text-2xl">
              {sideBarLayout?.map((item, index) => (
                <li key={index} className="flex items-center gap-4">
                  <item.icon />
                  <NavLink
                    end
                    className={({ isActive }) =>
                      isActive && "text-white border-b-2 border-cyan-600"
                    }
                    to={item.href}
                  >
                    {item.name}
                  </NavLink>
                </li>
              ))}
            </ul>
          </nav>

          <p className="font-bold mt-5">ACCOUNT</p>
          <div className="flex items-center gap-4 text-2xl">
            <IoSettingsOutline />
            <Link to={`/${role}`}>Settings</Link>
          </div>
        </div>
      </div>
    </aside>
  );
};

export default SideBar;
