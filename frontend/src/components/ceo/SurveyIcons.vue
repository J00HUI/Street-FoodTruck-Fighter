<template>
  <div class="iconsBox">
    <span class="circle">전체</span>
    <span>
      <div>이름</div>
      <div class="circleShdow"></div>
      </span>

  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.iconsBox {
  margin: 4% 4% 0 4%;
  display: flex;
}
.circle {
  font: 1rem "SCoreDream";
  display: flex;
  justify-content: center;
  align-items: center;
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  box-shadow: 0 0.25rem 0.5rem rgba(0, 0, 0, 0.2);
}
.circleShdow {
  width: 2rem;
  height: 1rem;
  background-color: rgb(167, 166, 166);
  border-radius: 50%;
}
</style>