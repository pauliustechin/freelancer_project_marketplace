import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const initialState = {
  "username": null,
  "userId": null,
  "userRoles": []
}

const useUsersStore = create(

  devtools((set) => ({

    user: initialState,

    loginUser: async (formData, navigate, searchForProjects) => {
      try {
        const { data } = await api.post(`/auth/login`, formData, { withCredentials: true });
        console.log(data)
        set(() => ({ user: {...data} }))
        navigate("/");
        searchForProjects();
      } catch (error) {
        console.log(error);
      }
    },

    registerNewAccount: async (formData, navigate) => {
      try {
        await api.post(`/auth/register`, formData);
        navigate("/");
      } catch (error) {
        console.log(error);
      }
    },

    logoutUser: async (navigate) => {
      try {
        await api.post(`/auth/logout`, { withCredentials: "include" });
        set(() => ({ user: initialState}))
        navigate("/");
      } catch (error) {
        console.log(error);
      }
    },


  })),
);

export default useUsersStore;