import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";
import router from "@/router/index.js";

export const useMypageStore = defineStore("Mypage", {
  state: () => {
    const userData = {
      businessNumber: "",
      email: "string",
      id: null,
      nickname: "",
      password: "string",
      phone: "",
      userType: "",
    };

    return {
      userData,
    };
  },
  actions: {
    getUserInfo(token) {
      console.log(token);

      axios({
        url: RF.user.getUserInfo(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          sessionStorage.setItem("user", res.data);
          if (res.data.userType === "CEO") {
            router.push("/ceomain");
          } else if (res.data.userType === "USER") {
            router.push("/");
          }
        })
        .catch(() => {
          alert("정보가 없습니다");
        });
    },
  },
});
