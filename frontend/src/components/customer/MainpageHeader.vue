<template>
  <header>
    <div>
      <div style="display: flex" v-if="isNickname">
        <img src="@/assets/markerIcon.svg" style="width: 2.5rem" alt />
        <address class="header__text-style">
          {{ kakaoStore.mapCenter.address }}
        </address>
      </div>

      <div class="header__name-style" v-if="isNickname">
        <span>
          <span
            class="header__text-style"
            style="margin: auto 0px auto 1.5rem; height: 100%"
            >{{ nickname }}님 😊</span
          >
        </span>

        <img
          src="@/assets/logout.svg"
          style="width: 7.6%; margin-left: 39%"
          alt
          @click="logout"
        />

        <span>
          <img src="@/assets/noticeIcon.svg" alt @click="gotoNotice" />
          <img
            src="@/assets/humanIcon.svg"
            style="margin-left: 1rem"
            alt
            @click="gotoMyprofile"
          />
        </span>
      </div>

      <div class="header__name-style" v-if="!isNickname">
        <span>
          <span
            class="header__text-style"
            style="margin: auto 0px auto 1.5rem; height: 100%"
            >로그인을 해주세요 😊</span
          >
        </span>

        <img src="@/assets/login.svg" style="width: 9%" alt @click="login" />
      </div>
    </div>

    <label for="menu-search" class="header__search-label">
      <img src="@/assets/SearchIcon.svg" style="width: 1.5rem" alt />
      <input type="search" id="menu-search" />
    </label>
  </header>
</template>

<script>
import { useKakaoStore } from "@/stores/kakao";
import router from "@/router";

export default {
  setup() {
    let curUserData = sessionStorage.getItem("user");
    let nickname = "";
    let isNickname = false;

    const kakaoStore = useKakaoStore();
    kakaoStore.setHeaderAddress();

    if (curUserData !== null) {
      nickname = JSON.parse(curUserData).nickname;
      isNickname = true;
    }

    // if (nickname != "") {
    //   alert("gg");
    //   nickname = JSON.parse(curUserData).nickname;
    // }

    function login() {
      router.push("/login");
    }

    function logout() {
      router.push("/login");
      isNickname = false;

      localStorage.clear();
      sessionStorage.clear();

      // 새로 고침 함수
    }

    function gotoNotice() {
      router.push("/notice");
    }

    function gotoMyprofile() {
      router.push("/myprofile");
    }

    return {
      kakaoStore,
      nickname,
      isNickname,
      gotoNotice,
      gotoMyprofile,
      login,
      logout,
    };
  },
};
</script>

<style scoped>
header {
  position: relative;
  width: 92%;
  background: var(--color-yellow-2);
  font-size: 1rem;
  border-radius: 0px 0px 1rem 1rem;
  padding: 4%;
  margin-bottom: 4rem;
}
address {
  display: inline-block;
  margin: auto 0 auto 0;
}
input {
  border: none;
  outline-style: none;
  padding: 0 0 0 1rem;
  width: 80%;
}
p {
  display: inline;
  margin: auto;
  text-align: center;
}
img {
  vertical-align: top;
}
.header__name-style {
  display: flex;
  justify-content: space-between;
  padding: 1rem;
}
.header__search-label {
  position: absolute;
  display: flex;
  box-sizing: border-box;
  left: 5%;
  width: 90%;
  height: 30%;
  top: 85%;
  border-radius: 0.75rem;
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  background-color: white;
  padding: 0 1rem 0 1rem;
}
.header__text-style {
  color: #303030;
  font-family: SCoreDream;
  font-style: normal;
}
</style>
