import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const useUsersStore = create(

  devtools((set) => ({
    user: "",
    userRoles: [],

    loginUser: async (formData, navigate, searchForProjects) => {
      try {
        console.log(formData)
        const { data } = await api.post(`/auth/login`, formData, {withCredentials: true});
        console.log(data);
        set(() => ({user: data.username, userRoles: [...data.roles]}))
        navigate("/projects");
        searchForProjects();
      } catch (error) {
        console.log(error);
      }
    }

  })),
);

export default useUsersStore;