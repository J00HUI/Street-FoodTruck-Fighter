import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";
import router from "@/router/index.js";

export const useAccountStore = defineStore("Account", {
  state: () => {
    const userData = {
      businessNumber: "",
      matchBusinessNumber: null,
      email: "",
      id: null,
      nickname: "",
      password: "",
      passwordCheck: "",
      phone: "",
      verificationCode: "",
      // isVerificationCode: "",
      userType: "",
    };

    const signUpCheck = {
      ceoSignUp: true,
      customerSignUp: false,
    };

    // const businessNumberToken = {
    //   token: "nXxMz2UXrVyikST9cBBJMOYPXONN%2BZncyNmHN%2BY0QGFI%2B3Rv1Ut7%2F2q5zZcpmuzHHoDwgvqJBfTehft6%2F6yyFA%3D%3D"
    // }

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
          console.log(res.data);
          sessionStorage.setItem("foodTruck", res.data.foodTruckId);
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
      if (
        this.userData.email != "" &&
        this.userData.password != "" &&
        this.userData.passwordCheck != "" &&
        this.userData.nickname != "" &&
        this.userData.phone != "" &&
        this.userData.verificationCode != ""
      ) {
        // 모든 정보 입력 검사
        if (this.userData.password === this.userData.passwordCheck) {
          // 비밀번호 검사
          if (this.signUpCheck.ceoSignUp === true) {
            // 사장님 회원가입
            if (this.userData.matchBusinessNumber != null) {
              // 사업자 등록번호 인증 확인
              axios({
                url: RF.user.signup(),
                method: "post",
                data: {
                  businessNumber: this.userData.businessNumber,
                  email: this.userData.email,
                  password: this.userData.password,
                  nickname: this.userData.nickname,
                  phone: this.userData.phone,
                  userType: "CEO",
                },
              })
                .then(() => {
                  alert("푸드트럭을 등록해주세요.")
                  router.push("/mytruck");
                })
                .catch(() => {
                  console.log("회원가입에 실패하였습니다.");
                });
            } else {
              alert("사업자 등록번호를 인증해주세요.");
            }
          } else if (this.signUpCheck.customerSignUp === true) {
            // 고객님 회원가입
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
                router.push("/login");
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

    checkBusinessNum() {
      if (this.userData.businessNumber != "") {
        axios({
          url:
            "https://api.odcloud.kr/api/nts-businessman/v1/status" +
            `?serviceKey=nXxMz2UXrVyikST9cBBJMOYPXONN%2BZncyNmHN%2BY0QGFI%2B3Rv1Ut7%2F2q5zZcpmuzHHoDwgvqJBfTehft6%2F6yyFA%3D%3D`,
          method: "post",
          data: {
            b_no: [this.userData.businessNumber],
          },
        })
          .then((res) => {
            if (res.data.match_cnt != null) {
              this.userData.matchBusinessNumber = res.data.match_cnt;
              alert("사업자 등록번호 인증에 성공하였습니다.");
            } else {
              alert("없는 사업자 등록번호입니다.");
            }
          })
          .catch(() => {
            console.log("사업자 등록번호 확인 실패");
          });
      } else {
        alert("사업자 등록번호를 입력해주세요.");
      }
    },

    sendNum() {
      if (this.userData.phone != "") {
        axios({
          url: RF.sms.sendSMS() + `?phoneNumber=${this.userData.phone}`,
          method: "post",
        })
          .then((res) => {
            console.log(res.data);
            alert("입력하신 휴대폰 번호로 인증 번호 메시지를 발송했습니다.");
          })
          .catch(() => {
            alert("인증 번호 메시지 발송에 실패하였습니다.");
          });
      } else {
        alert("휴대폰 번호를 입력해주세요.");
      }
    },

    checkNum() {
      if (this.userData.phone != "" && this.userData.verificationCode != "") {
        axios({
          url: RF.sms.checkSMS(),
          method: "post",
          data: {
            authToken: this.userData.verificationCode,
            phoneNumber: this.userData.phone,
          },
        })
          .then((res) => {
            console.log(res.data);
            console.log(this.userData.verificationCode);
            alert("본인 인증에 성공하였습니다.");
          })
          .catch(() => {
            alert("인증 번호가 틀립니다. 다시 확인해주세요.");
          });
      } else {
        alert("휴대폰 번호와 인증 번호를 확인해주세요.");
      }
    },
  },
});
