<template>
  <div class="container">
    <div class="pictureContainer">
      <div>
        <h3>푸드트럭 사진</h3>
      </div>
      <div>
        <!-- <img src="@/assets/foodtruck.svg" alt /> -->
        <img :src="detailStore.imgSet.img" alt="">
      </div>
    </div>
    <div class="infoContainer">
      <div>
        <h3>푸드트럭 정보</h3>
      </div>
      <div class="info">
        <div class="infoDetail">
          <a>상 호 명</a>
          <a class="a2">{{detailStore.aboutStore.data.name}}</a>
        </div>
        <div class="infoDetail">
          <a>운영시간</a>
          <a class="a2">{{detailStore.aboutStore.data.startTime}} ~ {{detailStore.aboutStore.data.endTime}} </a>
        </div>
        <div class="infoDetail">
          <a>전화번호</a>
          <a class="a2">{{detailStore.aboutStore.data.phone}}</a>
        </div>
        <div class="infoDetail">
          <a>위&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;치</a>
          <a class="a2">{{detailStore.aboutStore.data.address}}</a>
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
.a2{
  margin-left: 1rem;
}
.pictureContainer{
  width: 100%;
}
img{
  margin-bottom: 00.5rem;
  margin-left: 5%;
  width: 90%;
  height: 90%;
}
h3{
  margin-left: 1.5rem;
  margin-top: 0;
}
.info{
  margin-left: 1.5rem;
}
</style>