import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const usePayStore = defineStore("Pay", {
  state: () => {
    const bill = {
      // foodtruckId: sessionStorage.getItem(),
      menuList: [
        {
          count: 0,
          menuId: 0,
        },
      ],
    };
    let parameter = "";

    const payToken = {
      pg_token: "",
    };

    return {
      bill,
      parameter,
      payToken,
    };
  },
  actions: {
    payStart() {
      const token = localStorage.getItem("accessToken");

      axios({
        url: RF.pay.pay(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: this.bill,
      })
        .then((res) => {
          console.log("pay success " + res.data);
          console.log("res " + JSON.stringify(res.data));
          window.location.href = res.data.next_redirect_mobile_url;
          this.parameter = document.location.href.split("=");
          console.log(this.parameter + " para");
        })
        .catch(() => {
          console.log("pay error!!" + JSON.stringify(this.bill));
        });
    },

    paySuccess(token) {
      axios({
        url: RF.pay.paySuccess() + `?pg_token=${token}`,
        method: "get",
      })
        .then((res) => {
          console.log("pay success " + res.data);
        })
        .catch((res) => {
          console.log(this.parameter);
          console.log("pay error!!" + res);
        });
    },
  },
});
