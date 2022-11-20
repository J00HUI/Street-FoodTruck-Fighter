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

    const payReq = {
      partner_order_id: "",
      partner_user_id: "",
      tid: "",
    };

    return {
      bill,
      parameter,
      payToken,
      payReq,
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
          // console.log("pay success " + res.data);
          // console.log("res " + JSON.stringify(res.data));
          window.location.href = res.data.next_redirect_mobile_url;
          this.parameter = document.location.href.split("=");
          // console.log(this.parameter + " para");
          this.payReq.tid = res.data.tid;
          this.payReq.partner_order_id = res.data.partner_order_id;
          this.payReq.partner_user_id = res.data.partner_user_id;
          // console.log("pay ssss!! " + JSON.stringify(this.payReq));
        })
        .catch(() => {
          console.log("pay error!! " + JSON.stringify(this.payReq));
        });
    },

    paySuccess(token) {
      axios({
        url: RF.pay.paySuccess(token),
        method: "post",
        data: this.payReq,
      })
        .then((res) => {
          console.log("pay success " + res.data);
        })
        .catch(() => {
          console.log(this.parameter);
          console.log("pay error!!" + JSON.stringify(this.payReq));
        });
    },
  },
});
