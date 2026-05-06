import { create } from "zustand";
import { devtools } from "zustand/middleware";
import api from "../api/api";

const useBidsStore = create(

  devtools((set) => ({

    projectBids: [],
    freelancerBids: [],

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

    fetchFreelancerBids: async (userId) => {
      try {
        const { data } = await api.get(`/users/${userId}/bids`, {withCredentials: true});
        set(() => ({ freelancerBids: [...data.bids] }));
      } catch (error) {
        console.log(error);
      }
    },

    acceptBid: async (bidId, navigate, formData) => {
      try {
        await api.patch(`/bids/${bidId}`, null, { params: formData, withCredentials: true });
        navigate("/client")
      } catch (error) {
        console.log(error);
      }
    },

    updateBid: async (bidId, formData) => {
      try {
        await api.put(`/bids/${bidId}`, formData, { withCredentials: true });
      } catch (error) {
        console.log(error);
      }
    },

  })),
);

export default useBidsStore;
