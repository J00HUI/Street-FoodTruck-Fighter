<template>
  <div class="cardContainer">
    <div v-for="(item, idx) in list" :key="idx" class="card" @click="showDetail">
      <div class="picture">
        <img src="@/assets/hamburger.svg" alt />
      </div>
      <div class="detail">
        <div class="storeName">
          <h3>{{ detailStore.aboutStore.data.menuList[idx].name }}</h3>
        </div>
        <div class="orders">
          <h4>{{ detailStore.aboutStore.data.menuList[idx].price }}원</h4>
        </div>
      </div>
      <hr />
    </div>
  </div>

    <!-- <div v-for="(item, idx) in list" :key="idx" class="cardContainer" >
    <div class="card" @click="showDetail">
      <div class="picture">
        <img src="@/assets/hamburger.svg" alt />
      </div>
      <div class="detail">
        <div class="storeName">
          <h3>{{detailStore.aboutStore.data.menuList[idx].name}}</h3>
        </div>
        <div class="orders">
          <h4>{{detailStore.aboutStore.data.menuList[idx].price}}원</h4>
        </div>
      </div>
    </div>
    <hr />
  </div> -->
</template>

<script>
import { useStoreDetail } from "@/stores/customer/menu/storeDetail";
import router from "@/router";
export default {
  components: {},
  setup() {
    const detailStore = useStoreDetail();

    detailStore.getStoreInfo();

    let list = 0;
    list = detailStore.aboutStore.data.menuList;

    function showDetail() {
      router.push("/menudetail");
    }
    return {
      detailStore,
      list,
      showDetail,
    };
  },
};
</script>

<style scoped>
/***************** card style start ******************/
.cardContainer {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}
.card {
  display: flex;
  justify-content: left;
  flex-direction: row;
  align-items: center;
  width: 21.5rem;
  height: 6rem;
  border: 0;
}
hr {
  width: 100%;
  color: lightgray;
}
.picture {
  margin-right: 1rem;
}

/***************** card style end ******************/
</style>