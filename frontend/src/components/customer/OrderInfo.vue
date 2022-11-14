<template>
  <div class="container">
    <div class="pictureContainer">
      <div>
        <h3>푸드트럭 사진</h3>
      </div>
      <div>
        <img src="@/assets/foodtruck.svg" alt />
      </div>
    </div>
    <div class="infoContainer">
      <div>
        <h3>푸드트럭 정보</h3>
      </div>
      <div class="info">
        <div class="infoDetail">
          <a>상호명</a>
          <a2>{{detailStore.aboutStore.data.name}}</a2>
        </div>
        <div class="infoDetail">
          <a>운영시간</a>
          <a2>{{detailStore.aboutStore.data.startTime}} ~ {{detailStore.aboutStore.data.endTime}} </a2>
        </div>
        <div class="infoDetail">
          <a>전화번호</a>
          <a2>{{detailStore.aboutStore.data.phone}}</a2>
        </div>
        <div class="infoDetail">
          <a>위치</a>
          <a2>{{detailStore.aboutStore.data.address}}</a2>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useStoreDetail } from "@/stores/customer/menu/storeDetail";
export default {
  setup() {
    
    const detailStore = useStoreDetail();

    detailStore.getStoreInfo();

    return {
      detailStore,
    };
  },
};
</script>

<style scoped>
.container{
  margin-left: 1.5rem;
}
.info {
  display: flex;
  justify-content: left;
  flex-direction: column;
  align-items: left;
  width: 100%;
}
.infoDetail {
  display: flex;
  justify-content: left;
  flex-direction: row;
  align-items: flex-start;
  width: 100%;
}
a{
  width: 4rem;
}
a2{
  margin-left: 1rem;
}
</style>