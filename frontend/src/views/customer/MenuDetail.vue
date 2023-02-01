<template>
  <Header></Header>
  <div class="container">
    <div class="top">
      <div class="pictureContainer">
        <img class="picture" src="@/assets/coffee.svg" alt />
      </div>
      <div class="amount">
        <button @click="minusAmount">-</button>
        <div id="amount">
          <p>{{detailStore.amount}}</p>
        </div>
        <button @click="plusAmount">+</button>
      </div>
    </div>

    <div class="cardContainer">
      <div class="card">
        <div class="menuName">
          <!-- <h3 >{{detailStore.aboutStore.data.menuList['0'].name}}</h3> -->
          <!-- <h3 >{{detailStore.aboutStore.data.menuList}}</h3> -->
          <h3 >{{detailStore.menuDetail.name}}</h3>
        </div>
        <div class="detail">
          <p class="p2">{{detailStore.menuDetail.description}}</p>
        </div>
      </div>
    </div>

    <button class="payButton" @click="makeCart">
      <p @click="cartSave">{{detailStore.menuDetail.price * detailStore.amount}}원 담기</p>
    </button>
  </div>
</template>

<script>
import Header from "@/components/customer/BackButtonHeader.vue";
import { useStoreDetail } from "@/stores/customer/menu/storeDetail";
import { useCartStore } from "@/stores/customer/order/cart";
export default {
  components: {
    Header,
  },
  setup() {
    const detailStore = useStoreDetail();
    detailStore.getStoreInfo();
    // console.log('detailStore',detailStore.aboutStore);
    // console.log((detailStore.menuDetail.price) * (detailStore.amount) + ' price')
    // console.log('메뉴는 ' + detailStore.aboutStore.menuList)
    function minusAmount() {
      if (detailStore.amount > 1) {detailStore.amount--;}
      // else {detailStore.amount = 0}
      // console.log((detailStore.menuDetail.price) * (detailStore.amount) + ' price')
    }
    function plusAmount() {
      detailStore.amount++;
      // console.log((detailStore.menuDetail.price) * (detailStore.amount) + ' price')
    }
    const cartStore = useCartStore();
    const sessionCart = {
      menuName : "",
      price : 0,
      amount : 0,
      // src : ""
    }
    function makeCart(){
      cartStore.cart.foodtruckId = detailStore.foodtruck_id
      cartStore.cart.menuList[0].count = detailStore.amount
      cartStore.cart.menuList[0].menuId = detailStore.menuDetail.menuId
      cartStore.makeCustomerOrders()
      sessionCart.menuName = detailStore.menuDetail.name
      sessionCart.price = detailStore.menuDetail.price
      sessionCart.amount = detailStore.amount
      // sessionCart.src = detailStore.menuDetail.src

      sessionStorage.setItem("cart", JSON.stringify(this.sessionCart))
      // sessionStorage.setItem("bill", JSON.stringify(cartStore.cart))
      sessionStorage.setItem("bill", JSON.stringify(cartStore.cart))
      alert('카트에 담겼습니다!')

      // console.log(JSON.stringify(cartStore.cart) + ' cart')
      // console.log(JSON.stringify(detailStore.menuDetail) + ' !!!!kkkk')

    }
    function cartSave(){
      
    }
    return {
      detailStore,
      cartStore,
      sessionCart,
      minusAmount,
      plusAmount,
      makeCart,
      cartSave,
    };
  },
};
</script>

<style scoped>
.container {
  position: relative;
  height: 100%;
}
/***************** top style start ******************/
.top {
  background-color: #fff5dc;
  height: 20rem;
  display: flex;
  justify-content: center;
  flex-direction: column;
}
.pictureContainer {
  height: 80%;
}
.picture {
  width: 100%;
  height: 100%;
}
.amount {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 1rem;
  padding: 3px;
  background-color: #fff09f;
  border: 1.5px solid #ffdd2d;
  border-radius: 1.5rem;
  height: 1.8rem;
  width: 4.5rem;
}
button {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  width: 1.1rem;
  border: 0;
  border-radius: 100%;
  box-shadow: 1px 3px 5px -3px grey;
  font-weight: bold;
  color: gray;
}
p {
  margin-left: 0.3rem;
  margin-right: 0.3rem;
}
/***************** top style end ******************/

/***************** card style start ******************/
.cardContainer {
  height: 10rem;
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}
.card {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 21.5rem;

  height: auto;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px gray;
}
.menuName {
  height: 20%;
  margin-top: 1rem;
}
.detail {
  display: flex;
  height: 80%;
  width: 70%;
  margin-top: 2rem;
  justify-content: center;
}
.p2 {
  font-family: "SCoreDream";
  width: 70%;
  display: -webkit-box;
  overflow: hidden;
  word-break: break-all;
  word-wrap: break-word;
  text-overflow: ellipsis;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  text-align: center;
}
/***************** card style end ******************/

.payButton {
  position: absolute;
  bottom: 8rem;
  right: 4rem;
  width: 70%;
  height: 3.5rem;
  background-color: #ffe060;
  border-radius: 2rem;
  box-shadow: 2px 3px 15px -6px grey;
  font-weight: bold;
  font-family: "SCoreDream";
  text-align: center;
}
</style>