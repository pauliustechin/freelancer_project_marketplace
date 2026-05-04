import { useEffect } from "react";
import useUsersStore from "../store/usersStore";
import { Link, useNavigate, useLocation } from "react-router";

const Header = () => {

  const { user, logoutUser } = useUsersStore(state => state);
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogout = () => {
    logoutUser(navigate);
  }

  useEffect(() => {

  }, [user]);

  return (
    <header className="bg-white h-fit flex justify-between items-center p-8 shadow-lg">
      {((location.pathname === "/login") || (location.pathname === "/register")) 
      ? null : user.username ? 
      <button onClick={handleLogout} className="my-button w-20">Logout</button> : 
      <Link to="/login"><button className="my-button w-20">Login</button></Link>}
    </header>
  );
}

export default Header