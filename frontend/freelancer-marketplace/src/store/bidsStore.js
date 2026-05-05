import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const useBidsStore = create(

  devtools(() => ({

    placeBid: async (projectId, navigate, formData) => {
      try {
        await api.post(`/projects/${projectId}/bids`, formData, {withCredentials: true});
        navigate("/")
      } catch (error) {
        console.log(error);
      }
    },

  })),
);

export default useBidsStore;
