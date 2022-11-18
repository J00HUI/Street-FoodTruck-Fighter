<template>
  <div class="listContainer">
    <div v-for="(menu, idx) in detailStore.aboutStore.data.menuList" :key="idx" class="cardContainer">
      <div class="card" @click="showDetail(menu)" >
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
      </div>
      <div>
        <hr />
      </div>
    </div>
  </div>
</template>

<script>
import { useStoreDetail } from "@/stores/customer/menu/storeDetail";
import router from "@/router";
export default {
  components: {},
  setup() {
    const detailStore = useStoreDetail();

    detailStore.getStoreInfo();

    // let list = [];
    // list = detailStore.aboutStore.data.menuList;
    // console.log('asdfasd '+ JSON.stringify(list))

    function showDetail(menu) {
      detailStore.menuDetail = menu
      console.log(JSON.stringify(menu) + ' ASDF')
      router.push("/menudetail");
    }
    return {
      detailStore,
      // list,
      showDetail,
    };
  },
};
</script>

<style scoped>
/***************** card style start ******************/
.listContainer {
  width: 100%;
  height: 70%;
  overflow-y: auto;

  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.listContainer::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
.cardContainer {
  display: flex;
  justify-content: center;
  flex-direction: column;
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
  margin-left: 1rem;
}

/***************** card style end ******************/
</style>