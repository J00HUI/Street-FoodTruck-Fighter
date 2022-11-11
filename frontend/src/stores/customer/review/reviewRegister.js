import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const useMypageStore = defineStore("Mypage", {
  state: () => {
    const reviewRegist = {
      content: "",
      grade: 0,
      ordersId: 0,
      src: "",
    };

    return {
      reviewRegist,
    };
  },
  actions: {
    registReview() {
      const formData = new FormData();
      formData.append("content", this.registReview.content);
      formData.append("grade", this.registReview.grade);
      formData.append("ordersId", this.registReview.ordersId);
      formData.append("src", this.registReview.src);
      const token = localStorage.getItem("accessToken");

      axios({
        url: RF.user.registerFoodTruckReview(),
        formData,
        method: "post",
        headers: { Authorization: "Bearer " + token },
      })
        .then(() => {
          this.registReview = {
            content: "",
            grade: 0,
            ordersId: 0,
            src: "",
          };
        })
        .catch(() => {
          alert("error 발생!");
        });
    },
  },
});
