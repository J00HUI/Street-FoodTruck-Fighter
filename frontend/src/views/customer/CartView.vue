<template>
  <div class="a">
    <Header></Header>
    <div class="container">
      <div class="cardContainer">
        <div class="card">
          <div>
            <img class="picture" src="@/assets/coffee.svg" alt />
          </div>
          <div class="detail">
            <div class="storeName">
              <h3>{{this.sessionCart.menuName}}</h3>
            </div>
            <div class="orders">
              <h4>{{this.sessionCart.price * this.sessionCart.amount}}</h4>
            </div>
          </div>

          <div class="delete">
            <img class="deleteButton" src="@/assets/CloseButton.svg" alt />
          </div>
          <div class="amount">
            <button @click="minusAmount">-</button>
            <div id="amount">
              <p>{{this.sessionCart.amount}}</p>
            </div>
            <button @click="plusAmount">+</button>
          </div>
        </div>
      </div>
    </div>
    <button class="payButton" @click="[loginCheck(), payStart()]">
      <p>{{this.sessionCart.price * this.sessionCart.amount}}원 결제하기</p>
    </button>
  </div>
</template>

<script>
import Header from "@/components/customer/BackButtonHeader.vue";
import { useMenuStore } from "@/stores/customer/menu/menu";
import { useCartStore } from "@/stores/customer/order/cart";
import { useStoreDetail } from "@/stores/customer/menu/storeDetail";
import { usePayStore } from "@/stores/customer/pay/pay";
import router from "@/router";
export default {
  components: {
    Header,
  },
  setup() {
    const store = useMenuStore();
    const cartStore = useCartStore();
    const detailStore = useStoreDetail();
    const payStore = usePayStore();
    const sessionCart = JSON.parse(sessionStorage.getItem("cart"))
    function loginCheck() {
      if (
        localStorage.getItem("accessToken") == null ||
        localStorage.getItem("accessToken") == ""
      ) {
        alert("로그인이 필요한 서비스 입니다!");
        router.push("/login");
        
      }
    }
    function payStart(){
      payStore.bill = JSON.parse(sessionStorage.getItem("bill"))
      console.log(payStore.bill)
      payStore.payStart()
    }
    
    function minusAmount() {
      if (this.sessionCart.amount > 1) {
          this.sessionCart.amount--;
        }
      console.log(this.sessionCart.amount)
    }
    function plusAmount() {
      this.sessionCart.amount++;
      console.log(this.sessionCart.amount)
    }
    return {
      store,
      cartStore,
      detailStore,
      payStore,
      sessionCart,
      loginCheck,
      payStart,
      minusAmount,
      plusAmount,
    };
  },
};
</script>

<style scoped>
/***************** card style start ******************/
.cardContainer {
  height: 100%;
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}
.card {
  position: relative;
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 21.5rem;

  height: auto;
  border: 0.1rem solid lightgray;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px gray;
}

.picture {
  margin-left: 1rem;
}
.detail {
  margin-left: 0.5rem;
}

.delete {
  position: absolute;
  top: 0.7rem;
  right: 0.7rem;
}

.amount {
  display: relative;
  position: absolute;
  display: flex;
  direction: row;
  align-items: center;
  justify-content: center;
  bottom: 0.7rem;
  right: 0.7rem;
  padding: 3px;
  border: 1.5px solid #ffe060;
  border-radius: 0.9rem;
  height: 1.5rem;
  width: 4rem;
}
#amount {
  margin-left: 0.2rem;
  margin-right: 0.2rem;
}

button {
  background-color: transparent;
  border: 0;
}
/***************** card style end ******************/

.payButton {
  position: absolute;
  bottom: 10rem;
  right: 4rem;
  width: 70%;
  height: 3.5rem;
  background-color: #ffe060;
  border-radius: 2rem;
  box-shadow: 2px 3px 15px -6px gray;
  font-weight: bold;
  font-family: "SCoreDream";
}
.a {
  margin: 0;
  padding: 0;
  position: relative;
  width: 100%;
  height: 100%;
}
</style>