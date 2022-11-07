<template>
  <div class="a">
    <Header></Header>

      <div class="cardContainer">
        <div class="card">
          <div class="detail">
            <div class="orderState">
              <h3>주문 완료 알림</h3>
            </div>
            <div class="orders">
              <h4>슈크림 붕어빵</h4>
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
  height: 9rem;
}
.card {
  position: relative;
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 21.5rem;
  

  height: auto;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px gray;
  background-color: #FFF6DD;
}

.detail {
  margin-left: 0.5rem;
}

/***************** card style end ******************/
</style>