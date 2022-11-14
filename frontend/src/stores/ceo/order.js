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
    getNotAcceptedOrder() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.getNotAcceptedOrder(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log('-----허락안된 주문--------')
          console.log(res.data)
        })
        .catch((err) => {
          alert('허락안된 주문')
          console.log(err)
        });
    },
    getCeoOrders() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.getCeoOrders(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log('-----현재주문--------')
          console.log(res.data)
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
          console.log('-----모든주문--------')
          console.log(res.data)
        })
        .catch(() => {
          alert('주문목록 가져오기 실패')
        });
    },
    acceptOrders() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.acceptOrders(),
        method: "patch",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log('-----주문 성공--------')
          console.log(res)
        })
        .catch(() => {
          alert('주문성공 실패')
        });
    },
    cancelOrders() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.cancelOrders(),
        method: "patch",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log('-----주문 거절--------')
          console.log(res)
        })
        .catch(() => {
          alert('주문 거절 실패')
        });
    },
  }
})

