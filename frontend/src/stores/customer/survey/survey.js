import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const useCustomerSurveyStore = defineStore("CustomerSurvey", {
  state: () => {
    const surveyData = {
      address: "",
      category: "",

      latitude: null,
      longitude: null,
    };

    return {
      surveyData,
    };
  },

  actions: {
    setCustomerSurvey() {
      const token = localStorage.getItem("accessToken");

      axios({
        url: RF.survey.survey(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: {
          address: this.surveyData.address,
          category: this.surveyData.category,
          latitude: this.surveyData.latitude,
          longitude: this.surveyData.longitude,
        },
      })
        .then(() => {
          console.log("원해요 푸드트럭 등록 성공");
        })
        .catch(() => {
          console.log("원해요 푸드트럭 등록 실패");
        });
    },
  },
});
