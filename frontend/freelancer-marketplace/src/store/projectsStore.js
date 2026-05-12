import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const useProjectsStore = create(

  devtools((set) => ({
    projects: [],
    clientProjects: [],
    totalPages: 0,

    fetchProjects: async (filterParams) => {
      try {
        const { data } = await api.get(`/projects${filterParams ? filterParams : ""}`);
        set(() => ({ projects: [...data.content], totalPages: data.totalPages }));
      } catch (error) {
        console.log(error);
      }
    },

    fetchClientProjects: async (clientId) => {
      try {
        const { data } = await api.get(`/users/${clientId}/projects`, {withCredentials: true});
        set(() => ({ clientProjects: [...data] }));
      } catch (error) {
        console.log(error);
      }
    },

    createProject: async (formData, userId, navigate) => {
      try {
        const { data } = await api.post(`/users/${userId}/projects`, formData, { withCredentials : true })
        console.log(data)
        set((state) => ({ projects : [data, ...state.projects]}));
        navigate("/client")
      } catch (error) {
        console.log(error);
      }
    },

    editProject: async (formData, projectId, navigate) => {
      try {
        const { data } = await api.put(`/projects/${projectId}`, formData, { withCredentials: true });
        set((state) => ({ projects : state.projects.map(project => 
          project.id === projectId
          ? { ...project, ...data}
          : project),
        }))
        navigate("/client")
      } catch (error) {
        console.log(error);
      }
    }

  })),
);

export default useProjectsStore;
