import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const usePayStore = defineStore("Pay", {
  state: () => {
    const bill = {
      foodtruckId: 0,
      menuList: [
        {
          count: 0,
          menuId: 0,
        },
      ],
    };
    return {
      bill,
    };
  },
  actions: {
    payStart() {
      const token = localStorage.getItem("accessToken");

        axios({
            url: RF.pay.setCustomerOrders(),
            method: "post",
            headers: { Authorization: "Bearer " + token },
            data : this.cart
          })
            .then((res) => {
              console.log('cart making success!! ' + res.data)
            })
            .catch(() => {
              console.log('cart making error!!' + JSON.stringify(this.cart))
              
            });
    },
  },
});
