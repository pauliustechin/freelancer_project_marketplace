import { useEffect } from "react";
import useUsersStore from "./usersStore";

const AuthProvider = ({ children }) => {

  const { fetchCurrentUser } = useUsersStore(state => state);
  const { loggedOutOrAuthenticated } = useUsersStore(state => state); 

  useEffect(() => {
    fetchCurrentUser();
  }, [fetchCurrentUser]);

  if (!loggedOutOrAuthenticated) {
    return <div>Is loading...</div>;
  }

  return children;
};

export default AuthProvider;