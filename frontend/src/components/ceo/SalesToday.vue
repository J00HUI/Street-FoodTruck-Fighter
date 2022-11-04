<template>
  <div class="TodayView">
    <section class="TodayBox">
      <div class="TodayNum">6</div>
      <h1>오늘의 매출</h1>
      <div style="padding:0 1rem 0 1rem;">
        <div>붕어빵 100개</div>
        <div>잉어빵 10000000개</div>
        <div>잉어</div>
        <div>잉어</div>
        <div>잉어</div>
        <div>잉어</div>
      </div>
      <div class="TodayTotal">총 300000000원</div>
    </section>
  </div>
</template>

<script>
export default {
  setup() {
    const selectTime = [5, 10, 15, 20, 25]
    return {
      selectTime
    };
  }
};
</script>

<style scoped>
h1 {
  font-size: 1rem;
}
.TodayView {
  font-family: "SCoreDream";
  height: 28%;
}
.TodayBox {
  position: relative;
  box-sizing: border-box;
  width: 90%;
  height: auto;
  margin: 5%;
  padding: 5%;
  background-color: var(--color-purple-1);
  border-radius: 1rem;
}
.TodayNum {
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
.TodayTotal {
  position: absolute;
  top: 80%;
  left: 50%;
}
</style>