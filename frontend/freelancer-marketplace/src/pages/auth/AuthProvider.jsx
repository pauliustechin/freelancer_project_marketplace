import { useEffect } from "react";
import useUsersStore from "../../store/usersStore";
import { PulseLoader } from "react-spinners";

const AuthProvider = ({ children }) => {
  const { fetchCurrentUser, isLoading } = useUsersStore((state) => state);

  useEffect(() => {
    fetchCurrentUser();
  }, [fetchCurrentUser]);

  if (isLoading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <PulseLoader color="#38b9da" size={50} />
      </div>
    );
  }

  return children;
};

export default AuthProvider;
