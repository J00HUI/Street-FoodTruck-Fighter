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
    return {
        cart,
    };
  },
  actions:{
    registerOrder(){
        const token = localStorage.getItem("accessToken");

        axios({
            url: RF.user.registerFoodTruckReview(),
            method: "post",
            headers: { Authorization: "Bearer " + token },
            data : this.cart
          })
            .then((res) => {
              console.log(res.data)
            })
            .catch(() => {
              console.log('error')
            });
    }

    
  }
});
