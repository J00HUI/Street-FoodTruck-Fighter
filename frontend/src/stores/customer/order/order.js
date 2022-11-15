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

    return {
      myOrderData,
      orderData,
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

          //   this.orderData[0].foodtruckName;
          //   this.orderData[0].menuList[0].menuName;
          //   this.orderData[0].menuList[0].count;

          //   console.log(this.orderData[0].foodtruckName);
          //   console.log(this.orderData[0].menuList[0].menuName);
          //   console.log(this.orderData[0].menuList[0].count);
          //   console.log(res.data);
          //   console.log(res.data[0].foodtruckName);
          //   this.foodtruckName = res.data[0].foodtruckName;
          //   this.menuList[0].menuName = res.data[0].menuList[0].menuName;
          //   this.menuList[0].count = res.data[0].menuList[0].count;
          //   console.log(this.foodtruckName);
          //   console.log(res.data[0].menuList);
          //   console.log(res.data[0].menuList[0].menuName);
          //   console.log(res.data[0].menuList[0].count);
        })
        .catch(() => {
          console.log("내 주문 내역 가져오기 실패");
        });
    },
  },
});
