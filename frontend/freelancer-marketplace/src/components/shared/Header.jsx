import { useEffect } from "react";
import useUsersStore from "../../store/usersStore";
import { Link, useNavigate, useLocation } from "react-router";

const Header = () => {

  const { user, logoutUser } = useUsersStore(state => state);
  const navigate = useNavigate();
  const location = useLocation();

  const isAdmin = user.roles?.includes("ROLE_ADMIN");
  const isFreelancer = user.roles?.includes("ROLE_SELLER");
  const isClient = user.roles?.includes("ROLE_CLIENT");

  const handleLogout = () => {
    logoutUser(navigate);
  }

  useEffect(() => {

  }, [user]);

  return (
    <header className="bg-white h-fit flex justify-between items-center p-8 shadow-lg">

      <Link to="/" className="underline"><h1>GO TO HOME PAGE</h1></Link>

      {((location.pathname === "/login") || (location.pathname === "/register")) 
      ? null : user.username ? 
      <button onClick={handleLogout} className="my-button w-20">Logout</button> : 
      <Link to="/login"><button className="my-button w-20">Login</button></Link>}

      {(isAdmin && location.pathname !== "/admin") ? <Link to="/admin"><button className="my-button">AdminPanel</button></Link> : null}
      {(isFreelancer&& location.pathname !== "/freelancer") ? <Link to="/freelancer"><button className="my-button">FreelancerPage</button></Link> : null}
      {(isClient && location.pathname !== "/client") ? <Link to="/client"><button className="my-button">ClientPage</button></Link> : null}

    </header>
  );
}

export default Header