<template>
  <div class="header">
    <router-link to="/">
      <button>
        <img src="@/assets/CloseButton.svg" alt />
      </button>
    </router-link>
    <div>
      <h2>주문 완료</h2>
    </div>
  </div>
  <div class="container">
    <div class="pic">
      <img src="@/assets/PayOk.svg" alt />
    </div>
  </div>
</template>

<script>
export default {
  setup() {
    
  },
};
</script>

<style>
.header {
  display: flex;
  flex-direction: row;
  height: 1.5rem;
  width: 100%;
  justify-content: center;
  align-content: left;
}
button{
    margin-left: 1rem;
}
h2{
    margin-left: 1.5rem;
}
.container {
  display: flex;
  justify-content: center;
  align-content: center;
  height: 15rem;
  width: 100%;
}
.pic {
  width: 70%;
  height: 50%;
}
</style>