<template>
  <div>
    <span
      style="
        font-family: SCoreDream;
        font-style: normal;
        font-size: 105%;
        margin-left: 5%;
      "
      >내 주문 내역</span
    >
  </div>
  <br />

  <!-- <div style="margin-top: 1%">
    <div class="menuBox">
      <img src="@/assets/waffle.svg" class="truckImg" alt />

      <span class="truckName"
        >맛있는 와플<br /><span class="truckSub"
          >치즈버거 1개, 감자튀김 1개<br />
          남은 시간 : 10분</span
        ></span
      >
    </div>
  </div> -->

  <Splide
    :options="{ rewind: true }"
    aria-label="My Favorite Images"
    v-if="isNickname"
  >
    <SplideSlide v-for="(slide, Index) in orderStore.orderData" :key="Index">
      <div>
        <div class="menuBox">
          <img src="@/assets/waffle.svg" class="truckImg" alt />

          <span class="truckName" v-for="(order, orderDataIndex) in orderStore.orderData" :key="orderDataIndex"
            >{{ order.foodtruckName }}<br /><span
              class="truckSub" v-for="(menu, menuListIndex) in order.menuList" :key="menuListIndex"
              >{{ menu.menuName }}
              {{ menu.count }}개 <br /> </span
          ></span>
        </div>
      </div>
    </SplideSlide>
    <SplideSlide>
      <div>
        <div class="menuBox">
          <img src="@/assets/waffle.svg" class="truckImg" alt />

          <span class="truckName"
            >맛있는 와플<br /><span class="truckSub"
              >치즈버거 1개, 감자튀김 1개<br />
              남은 시간 : 10분</span
            ></span
          >
        </div>
      </div>
    </SplideSlide>
  </Splide>

  <div class="orderBox" v-if="!isNickname">
    <span style="font-family: SCoreDream; font-style: normal; font-size: 105%"
      >주문 내역이 없습니다.</span
    >
  </div>
</template>

<script>
import { useCustomerOrderStore } from "@/stores/customer/order/order.js";

// import "@splidejs/vue-splide/css";
import "@splidejs/vue-splide/css/skyblue";
// import '@splidejs/vue-splide/css/sea-green';
// import '@splidejs/vue-splide/css/core';

export default {
  setup() {
    const orderStore = useCustomerOrderStore();

    let curUserData = sessionStorage.getItem("user");
    let nickname = "";
    let isNickname = false;

    if (curUserData !== null) {
      nickname = JSON.parse(curUserData).nickname;
      orderStore.getCustomerOrders();
      isNickname = true;
    }

    return {
      orderStore,
      curUserData,
      nickname,
      isNickname,
    };
  },
};
</script>

<style scoped>
.orderBox {
  text-align: center;
  background: #ffffff;
  box-shadow: 0rem 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  border-radius: 1rem;
  width: 80%;
  height: 10%;
  margin-left: 10%;
  padding-top: 15%;
}
.menuBox {
  width: 80%;
  height: 10%;
  margin-left: 10%;
  padding-top: 7.5%;
  padding-bottom: 7.5%;

  background: #ffffff;
  box-shadow: 0rem 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  border-radius: 1rem;

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

  color: #000000;
}
</style>
  