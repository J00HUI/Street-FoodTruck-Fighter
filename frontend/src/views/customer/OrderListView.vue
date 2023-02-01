<template>
  <Header></Header>
  <div
    class="container"
    v-for="(orderList, index) in orderStore.orderAllData"
    :key="index"
  >
    <div>
      <div class="orderDate">
        <span>{{ orderList.orderDate }}</span>
        <span> - 주문완료</span>
      </div>
      <div class="orderDetail">
        <div
          class="cardContainer"
          v-for="(order, index) in orderList.ordersHistoryResList"
          :key="index"
        >
          <div class="card">
            <div>
              <img class="picture" src="@/assets/foodtruck.svg" alt />
            </div>
            <div class="detail">
              <div class="storeName">
                <h3>{{ order.foodtruckName }}</h3>
              </div>
              <div class="orders">
                <div>
                  <p>{{ order.menuDescription }}</p>
                </div>
              </div>
              <button
                class="reviewButton"
                @click="openModal()"
                v-if="order.reviewed == false"
              >
                리뷰작성
              </button>
              <button
                class="reviewButtonDone"
                @click="openModal()"
                disabled
                v-if="order.reviewed == true"
              >
                작성완료
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
import { useCustomerOrderStore } from "@/stores/customer/order/order";
export default {
  components: {
    Header,
    Modal,
  },
  setup() {
    const orderStore = useCustomerOrderStore();

    const showModal = ref(false);

    const openModal = () => {
      console.log("open");
      showModal.value = true;
    };

    const closeModal = () => {
      console.log("close");
      showModal.value = false;
    };

    orderStore.getCustomerOrdersAll();

    return {
      orderStore,
      showModal,
      openModal,
      closeModal,
    };
  },
};
</script>

<style scoped>
.container {
  //height: 100%;
  width: 100%;
  justify-content: center;
  align-content: center;
  margin-left: 2rem;
}
.orderDate {
  margin-left: 0.7rem;
  color: gray;
  font-style: bold;
}
/***************** card style start ******************/
.cardContainer {
  height: 100%;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}
.card {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 20.5rem;

  height: auto;
  border: 0.1rem solid lightgray;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px gray;
}

/***************** card style end ******************/

/***************** cardDetail style start ******************/
.picture {
  margin-left: 1rem;
  height: 5rem;
  width: auto;
}
.detail {
  position: relative;
  margin-left: 1rem;
  font-style: bold;
  width: 100%;
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
  //margin-right: 1rem;
  height: 33%;
  bottom: 0.5rem;
  right: 0px;
  border: 0.2rem solid #ffdd2d;
  background-color: #fff6dd;
  border-radius: 0.75rem;
  padding: 0.3rem;
}
.reviewButtonDone {
  position: relative;
  margin-left: 60%;
  height: 33%;
  bottom: 0.5rem;
  right: 0px;
  border: 0.2rem solid #898989;
  background-color: #f7f8f8;
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
