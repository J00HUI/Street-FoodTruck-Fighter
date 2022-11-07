import { defineStore } from "pinia";
import RF from "@/api/RF";
import axios from "axios";

export const useCeoOrderStore = defineStore("CeoOrder", {
  state: () => {
    return {
      viewToggle: false,
    }
  },
  actions: {
    getCeoOrders() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.foodtruck.getCeoOrders(),
        method: "get",
        headers: { Authorization: "Bearer" + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catc((err) => {
          console.log(err);
        });
    },
    getCeoOrdersAll() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.foodtruck.getCeoOrdersAll(),
        method: "get",
        headers: { Authorization: "Bearer" + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catc((err) => {
          console.log(err);
        });
    },
  }
})

