import useUsersStore from "../../store/usersStore";
import { Navigate, Outlet } from "react-router";

const PrivateRoute = ({ publicPage = false, freelancerOnly = false, clientOnly = false, adminOnly = false }) => {

  const { user, isLoading } = useUsersStore(state => state);
  const { username, roles } = user;
  const isFreelancer = roles?.includes("ROLE_SELLER");
  const isClient = roles?.includes("ROLE_CLIENT");
  const isAdmin = roles?.includes("ROLE_ADMIN");

  if(isLoading) {
    console.log("is loadiing ")
    console.log(isAdmin)
    console.log(user)
    console.log("finish ")
    return <div>Is loading...</div>
  }

  if(publicPage) {
    console.log("publix ")
    return username ? <Navigate to="/" /> : < Outlet />;
  }

  if(freelancerOnly){
    if(!isFreelancer){
      console.log("freelancer ")
      return username ? <Navigate to="/" /> : <Navigate to="/login" />;
    } else {
      return <Outlet />
    }
  }

  if(clientOnly){
    if(!isClient){
      console.log("client")
      return username ? <Navigate to="/" /> : <Navigate to="/login" />;
    } else {
      return <Outlet />
    }
  }

  if(adminOnly){
    if(!isAdmin){
      console.log("admin ")
          console.log(isAdmin)
    console.log(user)
      return username ? <Navigate to="/" /> : <Navigate to="/login" />;
    } else {
      return <Outlet />
    }
  }

  return username ? <Outlet /> : <Navigate to="/login" />;
}

export default PrivateRoute