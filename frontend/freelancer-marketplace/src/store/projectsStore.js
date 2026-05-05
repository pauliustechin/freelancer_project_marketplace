import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const useProjectsStore = create(

  devtools((set) => ({
    projects: [],

    searchForProjects: async () => {
      try {
        const { data } = await api.get(`/projects`, {withCredentials: true});
        set(() => ({ projects: [...data.content] }));
      } catch (error) {
        console.log(error);
      }
    },

  })),
);

export default useProjectsStore;
