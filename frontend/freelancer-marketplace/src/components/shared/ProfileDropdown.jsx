import { Menu } from "@headlessui/react";
import { CgProfile } from "react-icons/cg";
import { IoSettingsOutline } from "react-icons/io5";
import { IoMdExit } from "react-icons/io";
import { useEffect } from "react";
import useUsersStore from "../../store/usersStore";
import { useNavigate } from "react-router";

export default function ProfileDropdown({ theme }) {
  const { user, logoutUser } = useUsersStore((state) => state);
  const navigate = useNavigate();

  const userPath = user.roles?.includes("ROLE_CLIENT") ? "/client"
                  : user.roles?.includes("ROLE_SELLER") ? "/freelancer"
                  : user.roles?.includes("ROLE_ADMIN") && "/admin"


  const handleLogout = () => {
    logoutUser(navigate);
  };

  const handleNavigate = () => {
    navigate(userPath);
  };

  useEffect(() => {}, [user]);

  return (
    <Menu as="div" className="relative inline-block text-left z-11">
      <Menu.Button className={`flex items-center gap-2 btn btn-primary border-none w-full p-4 rounded-xl ${theme}`}>
        <CgProfile />
        Profile
      </Menu.Button>

      <Menu.Items className="absolute right-0 mt-2 w-48 bg-slate-300/90 shadow-lg rounded-lg p-4 flex flex-col gap-4 outline-none">
        <Menu.Item>
          <div className="flex items-center gap-2 cursor-pointer" onClick={handleNavigate}>
            <CgProfile />
            <p>My Profile</p>
          </div>
        </Menu.Item>

        <Menu.Item>
          <div className="flex items-center gap-2 cursor-pointer">
            <IoSettingsOutline />
            <p>Settings</p>
          </div>
        </Menu.Item>

        <Menu.Item>
          <div className="flex items-center gap-2 cursor-pointer" onClick={handleLogout}>
            <IoMdExit />
            <p>Logout</p>
          </div>
        </Menu.Item>
      </Menu.Items>
    </Menu>
  );
}
