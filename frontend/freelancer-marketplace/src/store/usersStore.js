import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const initialState = {
  "username": null,
  "userId": null,
  "roles": []
}

const useUsersStore = create(

  devtools((set) => ({

    user: initialState,
    isLoading: false,

    loginUser: async (formData, navigate) => {
      try {
        const { data } = await api.post(`/auth/login`, formData, { withCredentials: true });
        set(() => ({ user: {...data} }))
        navigate("/");
      } catch (error) {
        console.log(error);
      }
    },

    registerNewAccount: async (formData, navigate) => {
      try {
        await api.post(`/auth/register`, formData, { withCredentials: true });
        navigate("/");
      } catch (error) {
        console.log(error);
      }
    },

    logoutUser: async (navigate) => {
      try {
        await api.post(`/auth/logout`, {}, { withCredentials: true });
        set(() => ({ user: initialState }))
        navigate("/");
      } catch (error) {
        console.log(error);
      }
    },

    fetchCurrentUser: async () => {
      try {
        set({ isLoading : true })
        const { data } = await api.get("/auth/me", { withCredentials: true });
        set(() => ({ user: data }));
      } catch {
        set({ user: initialState});
      } finally {
        set({ isLoading: false })
      }
    },

  })),
);

export default useUsersStore;