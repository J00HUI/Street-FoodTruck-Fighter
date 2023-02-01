import { defineStore } from "pinia";
import RF from "@/api/RF";
import axios from "axios";

export const useCeoOrderStore = defineStore("CeoOrder", {
  state: () => {
    const notAcceptedOrder = [
      {
        acceptTime: "2022-11-21 09:10:22",
        accepted: true,
        menuResList: [
          {
            count: 5,
            menuName: "박범수의 따끈한 붕어빵",
          },
          {
            count: 2,
            menuName: "윤일준의 쌉쌀한 아아",
          },
          {
            count: 2,
            menuName: "이성훈의 달달한 자허블",
          },
        ],
        orderUserId: 0,
        ordersId: 3,
      },
    ];
    const acceptedOrder = [

      {
        acceptTime: "2022-11-21 09:10:22",
        accepted: true,
        menuResList: [
          {
            count: 5,
            menuName: "박범수의 따끈한 붕어빵",
          },
          {
            count: 2,
            menuName: "윤일준의 쌉쌀한 아아",
          },
          {
            count: 2,
            menuName: "이성훈의 달달한 자허블",
          },
        ],
        orderUserId: 0,
        ordersId: 3,
      },
      {
        acceptTime: "2022-11-21 09:10:22",
        accepted: true,
        menuResList: [
          {
            count: 5,
            menuName: "박범수의 따끈한 붕어빵",
          },
          {
            count: 2,
            menuName: "윤일준의 쌉쌀한 아아",
          },
          {
            count: 2,
            menuName: "이성훈의 달달한 자허블",
          },
        ],
        orderUserId: 0,
        ordersId: 3,
      },
      {
        acceptTime: "2022-11-21 09:10:22",
        accepted: true,
        menuResList: [
          {
            count: 5,
            menuName: "박범수의 따끈한 붕어빵",
          },
          {
            count: 2,
            menuName: "윤일준의 쌉쌀한 아아",
          },
          {
            count: 2,
            menuName: "이성훈의 달달한 자허블",
          },
        ],
        orderUserId: 0,
        ordersId: 3,
      },
    ];
    const orderTypeData = {
      doneDate: 0, //주문 수락에 사용
      notAcceptToggle: true,
      acceptToggle: false,
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
      const ceoId = JSON.parse(sessionStorage.getItem("user"))['id']
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.getNotAcceptedOrder(ceoId),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log("-----허락안된 주문--------");
          // if (Array.isArray(res.data)) {
            // this.notAcceptedOrder = res.data
          this.orderTypeData.notAcceptToggle = true
          // } else {
          //   this.notAcceptedOrder = null
          //   this.orderTypeData.notAcceptToggle = false
          // }

          console.log(res.data);
        })
        .catch((err) => {
          // alert("허락안된 주문");

          console.log(err);
        });
    },
    getCeoOrders() {
      const ceoId = JSON.parse(sessionStorage.getItem("user"))['id']
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.getCeoOrders(ceoId),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log("-----현재주문--------");
          if (Array.isArray(res.data)|| res.data.length > 2) {
            this.acceptedOrder = res.data
            this.orderTypeData.acceptToggle = true
          } else {
            this.acceptedOrder = null
            this.orderTypeData.acceptToggle = false
          }
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
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
        });
    },
  },
});
