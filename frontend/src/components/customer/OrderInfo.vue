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
          <a>맛있는 떡볶이</a>
        </div>
        <div class="infoDetail">
          <a>운영시간</a>
          <a>2022.10.18 09:00 ~ 18:00 </a>
        </div>
        <div class="infoDetail">
          <a>전화번호</a>
          <a>010-1234-5678</a>
        </div>
        <div class="infoDetail">
          <a>위치</a>
          <a>유성구 삼성화재 유성연수원</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.info {
  display: flex;
  justify-content: left;
  flex-direction: column;
  align-items: left;
}
.infoDetail {
  display: flex;
  justify-content: left;
  flex-direction: row;
  align-items: flex-start;
}
</style>