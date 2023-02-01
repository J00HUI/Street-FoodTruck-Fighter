<template>
  <div class="container">
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
    <div class="picContainer">
      <div class="pic">
        <img src="@/assets/PayOk.svg" alt />
      </div>
    </div>
  </div>
</template>

<script>
import { usePayStore } from "@/stores/customer/pay/pay";
export default {
  setup() {
    const payStore = usePayStore();
    const para = document.location.href.split("=");
    // console.log(para[1])
    const payToken = para[1]
    console.log(JSON.stringify(payToken))
    // payStore.payToken.pg_token = JSON.stringify(payToken)
    payStore.payToken.pg_token = payToken

    payStore.paySuccess(payStore.payToken.pg_token);

    return{
      payStore,
      para,
      payToken,
    }
  },
};
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