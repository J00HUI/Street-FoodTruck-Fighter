import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const useReviewRoadStore = defineStore("reviewRoad", {
  state: () => {
    const reviewRoad = {
      id: 0,
      userId: 0,
      ordersId: 0,
      grade: 0,
      content: "",
      src: "",
      regDate: "",
    };

    return {
      reviewRoad,
    };
  },
  actions: {
    getReview() {
      // const foodtruck_id = 1;
      const foodtruck_id = sessionStorage.getItem("foodtruckId")
      axios({
        url: RF.review.getReview(foodtruck_id),
        method: "get",
      })
        .then((res) => {
          console.log(RF.review.getReview(foodtruck_id));
          //   console.log(JSON.stringify(res.data) + ' res.data');
          this.reviewRoad = res.data;
            // console.log(JSON.stringify(this.reviewRoad) + ' reviewRoad.data');
          //   console.log(this.reviewRoad[0].userId)
        })
        .catch(() => {
          console.log("error");
        });
    },
  },
});
