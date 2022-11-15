<template>
  <div class="newOrderView">
    <h1>새로운 주문 내역</h1>
    <section class="newOrderBox">
      <div class="newOrderNum">6</div>
      <div>
        <div>붕어빵 100개</div>
        <div>잉어빵 10000000개</div>
      </div>
      <time class="newOrderTime">AM 10:10</time>
      <br />
      <button
        type="button"
        class="newOrderButton"
        v-for="(item, idx) in selectTime"
        :key="idx"
      >{{item}}분</button>
      <div style="margin: 1rem 0 0 50%">
        <button id="btn-cancle" @click="orderStore.cancelOrders" type="button" class="cancleButton">주문 취소</button>
        <button id="btn-accept" @click="orderStore.acceptOrders" type="button" class="acceptButton">주문 수락</button>
      </div>
    </section>
  </div>
</template>

<script>

import { useCeoOrderStore } from '@/stores/ceo/order';
export default {
  setup() {
    const orderStore = useCeoOrderStore()
    

    const selectTime = [5, 10, 15, 20, 25];
    orderStore.getNotAcceptedOrder()

    return {
      orderStore,
      selectTime,
    };
  }
};
</script>

<style scoped>
h1 {
  margin-left: 5%;
  font-size: 1rem;
}
.newOrderView {
  font-family: "SCoreDream";
}
.newOrderBox {
  position: relative;
  box-sizing: border-box;
  width: 90%;
  margin: 5%;
  padding: 5%;
  background-color: var(--color-purple-1);
  border-radius: 1rem;
  border: 4px solid transparent; 
  border-image:  linear-gradient(45deg, var(--color-pink-2) 0%, var(--color-purple-2) 100%);
  border-image-slice: 1;
  background-origin: border-box;
  background-clip:  border-box;
  
}
.newOrderNum {
  display: flex;
  position: absolute;
  top: -0.5rem;
  right: -0.5rem;
  width: 2rem;
  font: 1rem "SCoreDream";
  color: white;
  height: 2rem;
  border-radius: 1rem;
  background-color: red;
  justify-content: center;
  align-items: center;
}
.newOrderTime {
  position: absolute;
  top: 0.5rem;
  right: 2rem;
  font-size: 0.75rem;
}
.newOrderButton {
  background-color: white;
  width: 3rem;
  height: 3rem;
  border: none;
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  border-radius: 0.75rem;
  margin: 0.4rem;
}
.cancleButton {
  font: 0.5rem "SCoreDream";
  width: 4rem;
  height: 2rem;
  border: 0.1rem solid var(--color-pink-2);
  background-color: var(--color-pink-1);
  border-radius: 1rem;
}
.acceptButton {
  font: 0.5rem "SCoreDream";
  color: white;
  width: 4rem;
  height: 2rem;
  border: 0.1rem solid var(--color-purple-2);
  background-color: var(--color-purple-2);
  border-radius: 1rem;
  margin-left: 1rem;
}
.cancleButton:hover {
  cursor: pointer;
}
.acceptButton:hover {
  cursor: pointer;
}

</style>