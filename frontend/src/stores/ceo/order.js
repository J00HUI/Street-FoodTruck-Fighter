import { defineStore } from "pinia";
import RF from "@/api/RF";
import axios from "axios";

export const useCeoOrderStore = defineStore("CeoOrder", {
  state: () => {
    const notAcceptedOrder = [
      // 터미네이터
      {
        acceptTime: "yyyy-MM-dd HH:mm:ss",
        accepted: true,
        menuResList: [
          {
            count: 0,
            menuName: "string",
          },
        ],
        orderUserId: 0,
        ordersId: 0,
      },
    ];
    const acceptedOrder = [
      // 더미데이터
      // {
      //   acceptTime: "yyyy-MM-dd HH:mm:ss",
      //   accepted: true,
      //   menuResList: [
      //     {
      //       count: 0,
      //       menuName: "string"
      //     }
      //   ],
      //   orderUserId: 0,
      //   ordersId: 0
      // },
      // {
      //   acceptTime: "yyyy-MM-dd HH:mm:ss",
      //   accepted: true,
      //   menuResList: [
      //     {
      //       count: 0,
      //       menuName: "string"
      //     }
      //   ],
      //   orderUserId: 0,
      //   ordersId: 0
      // },
      // {
      //   acceptTime: "yyyy-MM-dd HH:mm:ss",
      //   accepted: true,
      //   menuResList: [
      //     {
      //       count: 0,
      //       menuName: "string"
      //     },
      //     {
      //       count: 0,
      //       menuName: "string"
      //     },
      //     {
      //       count: 0,
      //       menuName: "string"
      //     }
      //   ],
      //   orderUserId: 0,
      //   ordersId: 0
      // }
    ];
    const orderTypeData = {
      doneDate: 0, //주문 수락에 사용
    };
    return {
      viewToggle: false,
      acceptedOrder,
      notAcceptedOrder,
      orderTypeData,
    };
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
          console.log("-----허락안된 주문--------");
          console.log(res);
          console.log(res.data);
        })
        .catch((err) => {
          alert("허락안된 주문");

          console.log(err);
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
          console.log("-----현재주문--------");
          console.log(res);
          console.log(res.data);
        })
        .catch((err) => {
          alert("현재주문 가져오기");
          console.log(err);
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
          console.log("-----모든주문--------");
          console.log(res.data);
        })
        .catch(() => {
          alert("주문목록 가져오기 실패");
        });
    },
    acceptOrders(order_id) {
      const acceptData = {
        doneDate: this.orderTypeData.doneDate,
        ordersId: order_id,
      };

      const token = localStorage.getItem("accessToken");
      if (this.orderTypeData.doneDate !== 0) {
        axios({
          url: RF.orders.acceptOrders(order_id),
          method: "patch",
          headers: { Authorization: "Bearer " + token },
          data: acceptData,
        })
          .then((res) => {
            this.orderTypeData.doneDate = 0;
            console.log("-----주문 성공--------");
            console.log(res);
          })
          .catch(() => {
            alert("주문수락 실패");
          });
      } else {
        alert('시간을 선택해주세요')
      }
    },
    cancelOrders(order_id) {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.cancelOrders(order_id),
        method: "patch",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log("-----주문 거절--------");
          console.log(res);
        })
        .catch(() => {
          alert("주문 거절 실패");
        });
    },
  },
});
