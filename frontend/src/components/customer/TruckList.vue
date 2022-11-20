<template>
  <div class="truckScroll">
    <div style="margin-top: 7%"></div>

<div
  style="margin-top: 3%"
  v-for="(near, nearDataIndex) in nearStore.nearData"
  :key="nearDataIndex"
>
  <div class="truck" @click="goDetail(nearDataIndex)">
    <img src="@/assets/foodtruck.svg" class="truckImg" alt />

    <span class="truckName"
      >{{ near.name }} ⭐ {{ near.grade }}<br /><span class="truckSub"
        >{{ near.category }}<br />
        {{ near.address }}</span
      ></span
    >
  </div>
</div>
<!-- <div style="margin-top: 3%">
  <div class="truck">
    <img src="@/assets/foodtruck.svg" class="truckImg" alt />

    <span class="truckName"
      >맛있는 떡볶이 ⭐ 4.5<br /><span class="truckSub"
        >떡볶이, 오뎅, 닭꼬치<br />
        대전 유성구</span
      ></span
    >
  </div>
</div> -->
  </div>
</template>
  

<script>
import { useStoreDetail } from "@/stores/customer/menu/storeDetail.js";
import router from "@/router";

export default {
  setup() {
    const nearStore = useStoreDetail();

    function goDetail(nearDataIndex) {
      sessionStorage.setItem(
        "foodtruckId",
        nearStore.nearData[nearDataIndex].foodtruckId
      );

      router.push("/order");
    }

    return {
      nearStore,
      goDetail,
    };
  },
};
</script>


<style scoped>
.truck {
  width: 23.5rem;
  height: 6rem;

  background: #ffffff;
  box-shadow: 0rem 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  border-radius: 1rem;

  margin: auto;

  display: flex;
}

.truckImg {
  width: 20%;

  margin-left: 7%;
}

.truckName {
  font-family: SCoreDream;
  font-style: normal;
  font-weight: 500;
  font-size: 1rem;

  color: #000000;

  margin-left: 6%;
  margin-top: 3.5%;
}
.truckSub {
  font-family: "SCoreDream";
  font-style: normal;
  font-weight: 500;
  font-size: 0.75rem;

  color: #888888;
}
.truckScroll {
  overflow-y: auto;
  height: 63%;

  -ms-overflow-style: none; /* IE and Edge /
  scrollbar-width: none; / Firefox /
}
.truckScroll::-webkit-scrollbar {
  display: none; / Chrome, Safari, Opera*/
}
</style>