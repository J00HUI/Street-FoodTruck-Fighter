import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const useCartStore = defineStore("Cart", {
  state: () => {
    const cart = {
      foodtruckId: 0,
      menuList: [
        {
          count: 0,
          menuId: 0,
        },
      ],
    };
    const amount = 1;
    
    return {
        cart,
        amount,
    };
  },
  actions:{
    makeCustomerOrders(){
        const token = localStorage.getItem("accessToken");

        axios({
            url: RF.orders.setCustomerOrders(),
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
    }

    
  }
});
