import { useEffect } from "react";
import useUsersStore from "../../store/usersStore";
import { Link, useNavigate, useLocation, NavLink } from "react-router";

const Header = () => {
  const { user, logoutUser } = useUsersStore((state) => state);
  const navigate = useNavigate();
  const location = useLocation();

  const isAdmin = user.roles?.includes("ROLE_ADMIN");
  const isFreelancer = user.roles?.includes("ROLE_SELLER");
  const isClient = user.roles?.includes("ROLE_CLIENT");

  const handleLogout = () => {
    logoutUser(navigate);
  };

  useEffect(() => {}, [user]);

  return (
    <header className="bg-white h-fit w-full m-0 p-1">
      <div className="flex justify-between items-center p-8">
        <div className="flex items-center gap-4">
          <Link to="/" className="text-2xl font-bold ">
            <h1>[logo]FreelancerMarketplace</h1>
          </Link>
          <nav className="flex gap-2">
            <NavLink>Find work</NavLink>
            <NavLink>Find talent</NavLink>
            <NavLink>Why us</NavLink>
            <NavLink>Enterprise</NavLink>
          </nav>
        </div>

        {location.pathname === "/login" ||
        location.pathname === "/register" ? null : user.username ? (
          <button onClick={handleLogout} className="my-button w-20">
            Logout
          </button>
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

        {isAdmin && location.pathname !== "/admin" ? (
          <Link to="/admin">
            <button className="my-button">AdminPanel</button>
          </Link>
        ) : null}
        {isFreelancer && location.pathname !== "/freelancer" ? (
          <Link to="/freelancer">
            <button className="my-button">FreelancerPage</button>
          </Link>
        ) : null}
        {isClient && location.pathname !== "/client" ? (
          <Link to="/client">
            <button className="my-button">ClientPage</button>
          </Link>
        ) : null}
      </div>
      <hr className="border-gray-100"/>
    </header>
  );
};

export default Header;
