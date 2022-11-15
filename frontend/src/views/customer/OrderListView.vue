<template>
  <Header></Header>
  <div class="container">
    <div class="order">
      <div class="orderDate">
        <p>10/15 (토) º 주문완료</p>
      </div>
      <div class="orderDetail">
        <div class="cardContainer">
          <div class="card">
            <div>
              <img class="picture" src="@/assets/foodtruck.svg" alt />
            </div>
            <div class="detail">
              <div class="storeName">
                <h3>맛있는 햄버거</h3>
              </div>
              <div class="orders">
                <h4>치즈버거 1개, 감자튀김 1개</h4>
              </div>
              <button class="reviewButton" @click="openModal()">
                리뷰작성
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Modal class="modal" v-if="showModal" @close="closeModal" />
  </div>
  
</template>

<script>
import Header from "@/components/customer/BackButtonHeader.vue";
import Modal from "@/components/customer/ReviewModal.vue";
import { ref } from "vue";
export default {
  components: {
    Header,
    Modal,
  },
  setup() {
    const showModal = ref(false);

    const openModal = () => {
      console.log("open");
      showModal.value = true;
    };

    const closeModal = () => {
      console.log("close");
      showModal.value = false;
    };

    return {
      showModal,
      openModal,
      closeModal,
    };
  },
};
</script>

<style scoped>
.container{
  height: 100%;
  width: 100%;
}
.orderDate {
  margin-left: 2rem;
  color: gray;
  font-style: bold;
}
/***************** card style start ******************/
.cardContainer {
  height: 100%;
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}
.card {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 21.5rem;

  height: auto;
  border: 0.1rem solid lightgray;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px gray;
}

/***************** card style end ******************/

/***************** cardDetail style start ******************/
.picture {
  height: 7rem;
  width: auto;
}
.detail {
  position: relative;
  margin-left: 1rem;
  font-style: bold;
}
.storeName {
  height: 34%;
  /* padding-top: 1rem; */
}
.orders {
  padding-top: 0%;
  height: 33%;
}
.reviewButton {
  position: relative;
  margin-left: 60%;
  height: 33%;
  bottom: 0.5rem;
  right: 0px;
  border: 0.2rem solid #ffdd2d;
  background-color: #fff6dd;
  border-radius: 0.75rem;
  padding: 0.3rem;
}

/***************** cardDetail style end ******************/
.modal {
  position: fixed;
  top: 0;
  /* left: 30.7%; */
  width: 25.1rem;
  margin-left: 0rem;
}
</style>