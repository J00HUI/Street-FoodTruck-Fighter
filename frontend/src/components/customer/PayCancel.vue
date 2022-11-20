<template>
  <div class="container">
    <div class="header">
      <router-link to="/">
        <button>
          <img src="@/assets/CloseButton.svg" alt />
        </button>
      </router-link>
      <div>
        <h2>주문 취소</h2>
      </div>
    </div>
    <div class="picContainer">
      <div class="pic">
        <img src="@/assets/PayCancel.svg" alt />
      </div>
    </div>
  </div>
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
}
</script>

<style>
.container{
  display: flex;
  width: 100%;
  flex-direction: column;
  justify-content: space-around;
}
.header {
  position: relative;
  display: flex;
  flex-direction: row;
  height: 2.5rem;
  width: 100%;
  justify-content: center;
  align-content: center;
  margin-top: 1rem;
}
button {
  position: absolute;
  top: 1.2rem;
  left: 1rem;
  height: 80%;
}
h2 {
  position: absolute;
  top: 0.5rem;
  left: 9rem;
  height: 100%;
}
.picContainer {
  display: flex;
  justify-content: center;
  align-content: center;
  height: 15rem;
  width: 100%;
}
.pic {
  margin-top: 3rem;
  width: 70%;
  height: 50%;
}
</style>