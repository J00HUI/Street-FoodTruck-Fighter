<template>
  <div class="orderView">
    <h1>수락된 주문 내역</h1>
    <div style="overflow: scroll; height:500px">
    <section class="orderBox" v-for="i in 5"
    :key="i">
      <div>대기번호</div>
      <br>
      <div>
        <div>붕어빵 100개</div>
        <div>잉어빵 10000000개</div>
        <div>붕어</div>
      </div>
      <time class="orderTime">AM 10:10</time>
      <div style="margin: 1rem 0 0 50%">
        <button type="button" class="cancleButton">취소</button>
        <button type="button" class="acceptButton">완료</button>
      </div>
    </section>
  </div>
  </div>
</template>

<script>
export default {
  setup() {
    const selectTime = [5, 10, 15, 20, 25];
    return {
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