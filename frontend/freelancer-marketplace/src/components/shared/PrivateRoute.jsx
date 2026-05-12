import useUsersStore from "../../store/usersStore";
import { Navigate, Outlet } from "react-router";

const PrivateRoute = ({ publicPage = false, freelancerOnly = false, clientOnly = false, adminOnly = false }) => {

  const { user } = useUsersStore(state => state);
  const { username, roles } = user;
  const isFreelancer = roles?.includes("ROLE_SELLER");
  const isClient = roles?.includes("ROLE_CLIENT");
  const isAdmin = roles?.includes("ROLE_ADMIN");

  if(publicPage) {
    return username ? <Navigate to="/" /> : < Outlet />;
  }

  if(freelancerOnly){
    if(!isFreelancer){
      return username ? <Navigate to="/" /> : <Navigate to="/login" />;
    } else {
      return <Outlet />
    }
  }

  if(clientOnly){
    if(!isClient){
      return username ? <Navigate to="/" /> : <Navigate to="/login" />;
    } else {
      return <Outlet />
    }
  }

  if(adminOnly){
    if(!isAdmin){
      return username ? <Navigate to="/" /> : <Navigate to="/login" />;
    } else {
      return <Outlet />
    }
  }

  return username ? <Outlet /> : <Navigate to="/login" />;
}

export default PrivateRoute