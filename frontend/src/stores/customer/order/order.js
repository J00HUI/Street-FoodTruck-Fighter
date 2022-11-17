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
        acceptTime: "",
        canceled: true,
        done: true,
        foodtruckName: "",
        menuResList: [
          {
            count: 0,
            menuName: ""
          }
        ],
        ordersId: 0,
        reviewed: true
      }
    ]

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
          console.log(JSON.stringify(res.data) + ' res.data')
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
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          this.orderAllData = res.data;
          console.log(JSON.stringify(this.orderAllData) + ' orderAllData')
          console.log(JSON.stringify(res.data) + ' res.data All')
        })
        .catch(() => {
          console.log("내 주문 내역 가져오기 실패");
        });
    },
  },
});
