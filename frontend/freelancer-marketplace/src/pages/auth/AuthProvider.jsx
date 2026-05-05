import { useEffect } from "react";
import useUsersStore from "../../store/usersStore";

const AuthProvider = ({ children }) => {

  const { fetchCurrentUser, loggedOutOrAuthenticated } = useUsersStore(state => state);

  useEffect(() => {
    fetchCurrentUser();
  }, [fetchCurrentUser]);

  if (!loggedOutOrAuthenticated) {
    return <div>Is loading...</div>;
  }

  return children;
};

export default AuthProvider;