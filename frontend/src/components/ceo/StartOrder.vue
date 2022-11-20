<template>
  <div class="orderView">
    <h1>수락된 주문 내역</h1>
    <section class="orderBox" v-for="(order, o_i) in orderStore.acceptedOrder" :key="o_i">
      <div>주문번호: {{order.ordersId}}</div>
      <br />
      <div v-for="(menu, m_i) in order.menuResList"
      :key="m_i">
        <div>{{menu['menuName']}} {{menu['count']}}개</div>
      </div>
      <time class="orderTime">{{order['acceptTime'].slice(0,10)}} <br> {{order['acceptTime'].slice(10)}}</time>
      <div style="margin: 1rem 0 0 50%">
        <button type="button" @click="orderStore.cancelOrders(order.ordersId)" class="cancleButton">취소</button>
        <button type="button" class="acceptButton">완료</button>
      </div>
    </section>
  
  </div>
</template>

<script>
import { useCeoOrderStore } from "@/stores/ceo/order";
export default {
  setup() {
    const orderStore = useCeoOrderStore();
    const selectTime = [5, 10, 15, 20, 25];
    // orderStore.getCeoOrders();
    return {
      orderStore,
      selectTime
    };
  }
};
</script>

<style scoped>
h1 {
  margin-left: 5%;
  font-size: 1rem;
}
.orderView {
  font-family: "SCoreDream";
  position: sticky;
  min-height: 10px;
}
.orderBox {
  position: relative;
  box-sizing: border-box;
  width: 90%;
  margin: 5%;
  padding: 5%;
  border-radius: 0.25rem;
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
}
.orderTime {
  position: absolute;
  top: 1rem;
  right: 2rem;
  font-size: 0.75rem;
}
.orderButton {
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
  font-family: "SCoreDream";
  color: white;
  font-size: 0.5rem;
  width: 4rem;
  height: 2rem;
  border: 0.1rem solid var(--color-purple-2);
  background-color: var(--color-purple-2);
  border-radius: 1rem;
  margin-left: 1rem;
}
</style>