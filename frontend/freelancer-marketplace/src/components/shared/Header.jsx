import useUsersStore from "../../store/usersStore";
import { Link, NavLink } from "react-router";
import ProfileDropdown from "./ProfileDropdown";

const Header = () => {
  
  const { user } = useUsersStore((state) => state);

  return (
    <header className="bg-white h-fit w-full m-0 p-1">
      <div className="flex justify-between items-center p-8">
        <div className="flex items-center gap-4">
          <Link to="/" className="text-2xl font-bold ">
            <h1>[logo]FreelancerMarketplace</h1>
          </Link>
          <nav className="flex gap-2">
            <NavLink to="/projects">Find work</NavLink>
            <NavLink>Find talent</NavLink>
            <NavLink>Why us</NavLink>
            <NavLink>Enterprise</NavLink>
          </nav>
        </div>

        { user.username ? (
          <ProfileDropdown theme={"bg-slate-800"}></ProfileDropdown>
        ) : (
          <div className="flex gap-2 font-bold">
            <Link to="/login">
              <button className="btn primary">Log in</button>
            </Link>
            <Link to="/register">
              <button className="btn primary bg-slate-800 text-white">
                Sign up
              </button>
            </Link>
          </div>
        )}
      </div>
      <hr className="border-gray-100"/>
    </header>
  );
};

export default Header;
