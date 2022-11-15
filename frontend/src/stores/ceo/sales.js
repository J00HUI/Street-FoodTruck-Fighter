import { defineStore } from "pinia";
// import RF from "@/api/RF";
// import axios from "axios";

export const useCeoSalesStore = defineStore("CeoSales", {
  state: () => {
    const salesTypeData = {
      viewToggle: false,
      addEventIdx: 0,
    }
  return {salesTypeData}},
  actions: {}
})
