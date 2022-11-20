<template>
  <div style="text-align: center">
    <img src="@/assets/foodtruck.svg" style="width: 25%; margin-top: 40%" alt />

    <div style="margin-top: 3%">
      <span
        style="
          font-family: SCoreDream;
          font-style: normal;
          font-weight: 600;
          font-size: 170%; ;
        "
        >스트리트 푸드트럭 파이터</span
      >
    </div>

    <div style="margin-top: 1%">
      <span style="font-family: SCoreDream; font-style: normal; font-size: 100%"
        >아직 회원이 아니신가요?
        <router-link to="/signup" style="text-decoration: none">
          <span
            style="
              color: #ffcd00;
              font-family: SCoreDream;
              font-style: normal;
              font-size: 110%;
            "
            >회원가입</span
          ></router-link
        ></span
      >
    </div>

    <div style="margin-top: 10%">
      <form action="/action_page.php">
        <label for="email"></label>
        <input
          type="email"
          id="email"
          name="email"
          class="email"
          placeholder="이메일"
          style="text-align: center"
          v-model="accontStore.userData.email"
        />
        <br />
        <div style="margin-top: 3%">
          <label for="pwd"></label>
          <input
            type="password"
            id="pwd"
            name="pwd"
            class="pwd"
            placeholder="비밀번호"
            style="text-align: center"
            v-model="accontStore.userData.password"
          />
        </div>
        <div style="margin-top: 10%">
          <input
            type="button"
            @click="accontStore.login"
            value="로그인"
            class="loginButton"
          />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { useAccountStore } from "@/stores/account";

export default {
  setup() {
    const accontStore = useAccountStore();

    return {
      accontStore,
    };
  },
};
</script>

<style scoped>
.email,
.pwd {
  /* width: 331px; */
  width: 20rem;
  /* width: 90%; */
  /* width: 350px; */
  height: 4rem;
  /* height: 80px; */
  /* width: 600px; */
  /* width: 50vw; */
  /* height: 5vh; */

  background: #f7f8f8;
  border-radius: 1rem;

  border: none;
  outline-style: none;
}
input.email::-webkit-input-placeholder {
  background-image: url(@/assets/email.svg);
  background-size: contain;
  background-position: 7rem center;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
input.pwd::-webkit-input-placeholder {
  background-image: url(@/assets/password.svg);
  background-size: contain;
  background-position: 7rem center;
  background-repeat: no-repeat;
  /* text-align: center; */
  text-indent: 0;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.8rem;

  color: #898989;
}
.loginButton {
  width: 15rem;
  height: 4rem;

  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;

  filter: drop-shadow(0rem 0.625rem 1.3rem rgba(150, 170, 250, 0.3));

  background: #ffe060;
  border-radius: 2rem;
  border: none;
  outline-style: none;
}
</style>