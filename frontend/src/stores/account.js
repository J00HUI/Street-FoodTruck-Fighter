import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";
import router from "@/router/index.js";

export const useAccountStore = defineStore("Account", {
  state: () => {
    const userData = {
      businessNumber: "",
      email: "",
      id: null,
      nickname: "",
      password: "",
      passwordCheck: "",
      phone: "",
      userType: "",
    };

    const signUpCheck = {
      ceoSignUp: true,
      customerSignUp: false,
    };

    return {
      userData,
      signUpCheck,
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
          } else if (res.data.userType === "CUSTOMER") {
            router.push("/");
          }
        })
        .catch(() => {
          alert("정보가 없습니다");
        });
    },

    signUp() {
      if (this.userData.email != "" && this.userData.password != "" && this.userData.passwordCheck != "" && this.userData.nickname != "" && this.userData.phone != "") { // 모든 정보 입력 검사
        if (this.userData.password === this.userData.passwordCheck) { // 비밀번호 검사
          if (this.signUpCheck.ceoSignUp === true) { // 사장님 회원가입
            alert("사장님");
          } else if (this.signUpCheck.customerSignUp === true) { // 고객님 회원가입
            axios({
              url: RF.user.signup(),
              method: "post",
              data: {
                email: this.userData.email,
                password: this.userData.password,
                nickname: this.userData.nickname,
                phone: this.userData.phone,
                userType: "CUSTOMER",
              },
            })
              .then((res) => {
                console.log(res);
              })
              .catch(() => {
                alert("회원가입에 실패하였습니다.");
              });
          }
        } else {
          alert("비밀번호가 다릅니다. 다시 확인해주세요.");
        }

      } else {
        alert("모든 정보를 입력해주세요.");
      }
    },
  },
});
