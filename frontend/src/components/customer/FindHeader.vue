<template>
  <header class="header2">
    <button @click="goBack">
      <img src="@/assets/ceo/nav2Back.svg" alt />
    </button>
    <img src="@/assets/markerIcon.svg" alt />
    <address style="margin: auto; font-style: normal">{{ kakaoStore.mapCenter.address }}</address>
    <img src="@/assets/noticeIcon.svg" alt />
    <img src="@/assets/humanIcon.svg" style="margin-left: 1rem" alt />
  </header>
</template>

<script>
import { useKakaoStore } from "@/stores/kakao";

export default {
  setup() {
    const kakaoStore = useKakaoStore();
    kakaoStore.setHeaderAddress();

    function goBack() {
      window.history.back();
    }
    return {
      kakaoStore,
      goBack,
    };
  },
};
</script>

<style scoped>
button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: none;
}
.header2 {
  font-family: "SCoreDream";
  display: flex;
  width: 90%;
  margin: 5%;
  justify-content: center;
  align-items: center;
}
</style>