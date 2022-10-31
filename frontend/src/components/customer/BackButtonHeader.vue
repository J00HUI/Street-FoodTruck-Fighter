<template>
  <header class="header">
    <div class="back">
      <button @click ="goBack">
        <img src="@/assets/Back-Navs.svg" alt />
      </button>
    </div>
    <div class="home">
      <button @click ="goHome">
        <img src="@/assets/Home.svg" alt />
      </button>
      <button @click ="goCart">
        <img src="@/assets/Cart.svg" style="margin-left:1rem;" alt />
      </button>
    </div>
  </header>
</template>

<script>
export default {
  setup() {
    function goBack() {
      window.history.back()
    }
    return {
      goBack,
    }
  }
};
</script>

<style scoped>
button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: none;
  align-items: left;
}
.header {
  font-family: "SCoreDream";
  display: flex;
  width: 90%;
  margin: 5%;
  justify-content: space-between;
  align-items: center;
}
.back{
  align-self: left;
}
.home{
  align-self: right;
}

</style>