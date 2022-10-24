<template>
 <header>
  <div>
    <div class="">
      <img src="@/assets/markerIcon.svg" alt="">
      <address>주소</address>
    </div>
    <div>
      <span>이름</span>
      <img src="@/assets/noticeIcon.svg" alt="">
      <img src="@/assets/humanIcon.svg" alt="">
    </div>
  </div>
  <input type="search">
 </header>
</template>

<script>
export default {


}
</script>

<style scoped>
header {
  position: relative;
  width: 90%;
  background: var(--color-purple-2);
  font-size: 1rem;
  border-radius: 0px 0px 1rem 1rem;
  padding: 5%;
}
address {
  display: inline;
}
input {
  position: absolute;
  left: 5%;
  width: 90%;
  height: 3rem;
  top: calc(100%  - 1.5rem);
  border-radius: 0.75rem;
}
</style>
