import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";
import router from "@/router/index.js";
export const useAccountStore = defineStore("Account", {
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
    login() {
      axios({
        url: RF.user.login(),
        method: "post",
        data: {
          email: this.userData.email,
          password: this.userData.password,
        },
      })
        .then((res) => {
          localStorage.setItem("accessToken", res.data.accessToken);
          this.getUserInfo(res.data.accessToken);
        })
        .catch(() => {
          alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
        });
    },
    getUserInfo(token) {
      console.log(token);

      axios({
        url: RF.user.getUserInfo(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          sessionStorage.setItem("user", JSON.stringify(res.data));
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
