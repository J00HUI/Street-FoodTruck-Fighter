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
        url: RF.orders.getCeoOrders(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          alert('현재주문 가져오기')
          console.log(err)
        });
    },
    getCeoOrdersAll() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.getCeoOrdersAll(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log(res)
        })
        .catch(() => {
          alert('주문목록 가져오기 실패')
        });
    },
  }
})

