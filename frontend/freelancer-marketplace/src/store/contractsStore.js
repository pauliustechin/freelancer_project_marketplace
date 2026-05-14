import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const useContractsStore = create(

  devtools((set) => ({

    contracts: [],

    fetchClientContracts: async () => {
      try {
        const { data } = await api.get(`/contracts/client`);
        console.log(data)
        console.log(data.content)
        set(() => ({ contracts: [...data.content] }))
      } catch (error) {
        console.log(error);
      }
    },

    fetchFreelancerContracts: async () => {
      try {
        const { data } = await api.get(`/contracts/freelancer`);
        set(() => ({ contracts: [...data.content] }))
      } catch (error) {
        console.log(error);
      }
    },

  })),
);

export default useContractsStore;