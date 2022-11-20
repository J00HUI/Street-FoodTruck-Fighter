<template>
  <div class="newOrderView">
    <h1>새로운 주문 내역</h1>
    <div v-if="orderStore.orderTypeData.notAcceptToggle">
      <section
        class="newOrderBox"
        v-for="(order, o_i) in [orderStore.notAcceptedOrder[0]]"
        :key="o_i"
      >
        <div class="newOrderNum">{{order.ordersId}}</div>
        <div v-for="(menu, m_i) in order.menuResList" :key="m_i">
          <div>{{menu['menuName']}} {{menu['count']}}개</div>
        </div>
        <time class="newOrderTime">
          {{order['acceptTime'].slice(0,10)}}
          <br />
          {{order['acceptTime'].slice(10)}}
        </time>
        <br />
        <label
          class="newOrderButton"
          @click="getDoneDate(time)"
          v-for="(time, idx) in selectTime"
          :key="idx"
          :for="`time-selet-${time}`"
        >
          <div>
            <input
              :id="`time-selet-${time}`"
              name="time-selet-radeo"
              class="newOrderInput"
              type="radio"
              :value="time"
            />
          </div>
          <div class="newOrderText">{{time}}분</div>
        </label>
        <div style="margin: 1rem 0 0 50%">
          <button
            id="btn-cancle"
            @click="orderStore.cancelOrders(order.ordersId)"
            type="button"
            class="cancleButton"
          >주문 취소</button>
          <button
            id="btn-accept"
            @click="orderStore.acceptOrders(order.ordersId)"
            type="button"
            class="acceptButton"
          >주문 수락</button>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import { useCeoOrderStore } from "@/stores/ceo/order";

export default {
  setup() {
    const orderStore = useCeoOrderStore();

    const selectTime = [5, 10, 15, 20, 25];

    function getDoneDate(time) {
      orderStore.orderTypeData.doneDate = time;
    }

    
    return {
      orderStore,
      selectTime,
      getDoneDate
    };
  }
};
</script>

<style scoped>
h1 {
  margin-left: 5%;
  font-size: 1rem;
}
[type="radio"] {
  vertical-align: middle;
  appearance: none;
  outline: none;
}
input[type="radio"]:checked {
  border: 4px solid black;
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
  border-image: linear-gradient(
    45deg,
    var(--color-pink-2) 0%,
    var(--color-purple-2) 100%
  );
  border-image-slice: 1;
  background-origin: border-box;
  background-clip: border-box;
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
  display: inline-block;
  position: relative;
  background-color: white;
  width: 3rem;
  height: 3rem;
  border: none;
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
  border-radius: 0.75rem;
  margin: 0.4rem;
}
.newOrderInput {
  position: absolute;
  display: flex;
  top: -0.2rem;
  left: -0.3rem;
  border-radius: 0.75rem;
  width: 100%;
  height: 100%;
  justify-content: center;
  align-items: center;
}
.newOrderText {
  font: 0.75rem "SCoreDream";
  position: absolute;
  display: flex;
  width: 100%;
  height: 100%;
  justify-content: center;
  align-items: center;
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