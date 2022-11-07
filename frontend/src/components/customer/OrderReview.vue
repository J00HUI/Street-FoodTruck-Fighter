<template>
  <div>
    <div class="cardContainer">
      <div class="card">
        <!-- 사진 -->
        <div class="picture">
          <img src="@/assets/Burger.svg" alt />
        </div>
        <hr />
        <div class="detail">
          <!-- 이름과 별점 -->
          <div class="writer">
            <h3>name</h3>
            <p>star</p>
          </div>
          <!-- 리뷰 -->
          <div class="review">
            <p>너무 맛있는 인생맛집 발견 ㅠㅠㅠㅠ</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
/* card style start */
.cardContainer {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}
.card {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  width: 21.5rem;
  height: 17rem;
  border: 0.1rem solid #ffffff;
  border-radius: 1rem;
  box-shadow: 2px 3px 15px -6px grey;
}
.picture {
  height: 13rem;
}
hr {
  width: 100%;
  color: yellow;
}
.writer {
  display: flex;
  justify-content: space-around;
  flex-direction: row;
  align-items: left;
  height: 2.5rem;
}
.review {
  height: 30%;
  margin-top: 5rem;
}
/* cart style end */
</style>