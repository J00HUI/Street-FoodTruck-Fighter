import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const useCustomerOrderStore = defineStore("CustomerOrder", {
  state: () => {
    const myOrderData = {
      foodtruckName: "",
      menuList: [
        {
          menuName: "",
          count: null,
        },
      ],
    };

    const orderData = [
      {
        acceptTime: "",
        foodtruckName: "",

        menuList: [{ menuName: "", count: null }],
        ordersId: null,
      },
    ];

    const orderAllData = [
      {
        orderDate: "",
        ordersHistoryResList: [
          {
            acceptTime: "",
            canceled: false,
            done: false,
            foodtruckName: "",
            menuDescription: "",
            ordersId: 0,
            reviewed: false,
          },
        ],
      },
    ];

    return {
      myOrderData,
      orderData,
      orderAllData,
    };
  },

  actions: {
    getCustomerOrders() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.getCustomerOrders(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          this.orderData = res.data;
          console.log(JSON.stringify(res.data) + " res.data");
        })
        .catch(() => {
          console.log("내 주문 내역 가져오기 실패");
        });
    },

    getCustomerOrdersAll() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.orders.getCustomerOrdersAll(),
        method: "get",
        // headers: {
        //   Authorization:
        //     "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXNAY3VzLmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJpc3MiOiJzc2FmeS5jb20iLCJleHAiOjE2NjkwMTA3MzcsImlhdCI6MTY2ODkyNDMzN30.G3joKu9GartZu3iH5bhc7i2lpeO6y5Y42i4qUU0ijSHOOmtCtdQ9qmTBR79Rc1RIVrO7iEf0F50WRfQ-Atve_Q",
        // },
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log(res);
          console.log(token);
          this.orderAllData = res.data;
          console.log(JSON.stringify(this.orderAllData) + " orderAllData");
          // console.log(JSON.stringify(res.data) + " res.data All");
          // console.log(res.data);
        })
        .catch(() => {
          console.log("내 이전 주문 내역 가져오기 실패");
        });
    },
  },
});
