import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const useContractsStore = create(

  devtools((set) => ({

    contracts: [],

    fetchClientContracts: async () => {
      try {
        const { data } = await api.get(`/contracts/client`, { withCredentials: true });
        console.log(data)
        console.log(data.content)
        set(() => ({ contracts: [...data.content] }))
      } catch (error) {
        console.log(error);
      }
    },

    fetchFreelancerContracts: async () => {
      try {
        const { data } = await api.get(`/contracts/freelancer`, { withCredentials: true });
        set(() => ({ contracts: [...data.content] }))
      } catch (error) {
        console.log(error);
      }
    },

  })),
);

export default useContractsStore;