<template>
  <div class="a">
    <Header></Header>

    <div class="cardContainer">
      <div class="card">
        <div class="detail">
          <div class="orderState">
            <h2>주문 완료 알림</h2>
          </div>
          <div class="orders">
            <h4>슈크림 붕어빵 6개</h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/customer/BackButtonHeader.vue";
export default {
  components: {
    Header,
  },
  setup() {
    return {};
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
  height: 10rem;
}
.card {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 21.5rem;

  height: auto;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px gray;
  background-color: #fff6dd;
}

.detail {
  display: flex;
  flex-direction: column;
  margin-left: 1rem;
  width: 100%;
  height: 100%;
}
.orderState{
    height: 20%;
}
.orders{
    height: 80%;
    margin-top: 0.2rem;
    margin-left: 1rem;
}

/***************** card style end ******************/
</style>