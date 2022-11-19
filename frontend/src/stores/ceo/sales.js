import { defineStore } from "pinia";
import RF from "@/api/RF";
import axios from "axios";

export const useCeoSalesStore = defineStore("CeoSales", {
  state: () => {
    const salesTypeData = {
      viewToggle: false,
      addEventIdx: 0,
    }
  return {salesTypeData}},
  actions: {
    getStatistics() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.business.businessPath(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log(res)
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setStatistics() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.business.businessPath(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: this.scheduleAddForm
      })
        .then((res) => {
          console.log(res)
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }
})
