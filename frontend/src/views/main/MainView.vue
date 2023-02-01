<template>
  <MainpageHeader></MainpageHeader>
  <MyOrder></MyOrder>
  <FoodtruckMenu></FoodtruckMenu>
  <WantFoodtruck></WantFoodtruck>
</template>

<script>
import { useKakaoStore } from "@/stores/kakao";
import { useStoreDetail } from "@/stores/customer/menu/storeDetail.js";
import MainpageHeader from "@/components/customer/MainpageHeader.vue";
import MyOrder from "@/components/customer/MyOrder.vue";
import FoodtruckMenu from "@/components/customer/FoodtruckMenu.vue";
import WantFoodtruck from "@/components/customer/WantFoodtruck.vue";

export default {
  components: {
    MainpageHeader,
    MyOrder,
    FoodtruckMenu,
    WantFoodtruck,
  },
  setup() {
    const kakaoStore = useKakaoStore();
    kakaoStore.setHeaderAddress();

    const nearStore = useStoreDetail();

    function location() {
      navigator.geolocation.getCurrentPosition(function (position) {
        sessionStorage.setItem("address", kakaoStore.mapCenter.address);
        sessionStorage.setItem("latitude", position.coords.latitude);
        sessionStorage.setItem("longitude", position.coords.longitude);
      });
    }

    location();
    nearStore.getNearStoreInfo();

    return {
      kakaoStore,
      nearStore,
      location,
    };
  },
};
</script>

<style>
</style>