import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const useBidsStore = create(

  devtools((set) => ({

    projectBids: [],

    placeBid: async (projectId, navigate, formData) => {
      try {
        await api.post(`/projects/${projectId}/bids`, formData, {withCredentials: true});
        navigate("/")
      } catch (error) {
        console.log(error);
      }
    },

      fetchBidsByProject: async (projectId) => {
      try {
        const { data } = await api.get(`/projects/${projectId}/bids`, {withCredentials: true});
        set(() => ({ projectBids: [...data.bids] }));
      } catch (error) {
        console.log(error);
      }
    },

  })),
);

export default useBidsStore;
