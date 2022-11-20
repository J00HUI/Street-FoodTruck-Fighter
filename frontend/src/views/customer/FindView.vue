<template>
  <FindHeader></FindHeader>
  <FindMapList></FindMapList>
</template>

<script>
import { useKakaoStore } from "@/stores/kakao";
import { useStoreDetail } from "@/stores/customer/menu/storeDetail.js";
import FindHeader from "@/components/customer/FindHeader.vue";
import FindMapList from "@/components/customer/FindMapList.vue";

export default {
  components: {
    FindHeader,
    FindMapList,
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