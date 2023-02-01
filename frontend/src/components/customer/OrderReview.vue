<template>
  <div class="scroll">
    <div v-for="(item, idx) in list" :key="idx" class="cardContainer">
      <div class="card">
        <!-- 사진 -->
        <div class="picture">
          <img src="@/assets/Burger.svg" alt />
        </div>
        <hr />
        <div class="detail">
          <!-- 이름과 별점 -->
          <div class="writer">
            <h3>{{ reviewStore.reviewRoad[idx].userId }} 님 &nbsp;</h3>
            <p>⭐ {{ reviewStore.reviewRoad[idx].grade }}</p>
          </div>
          <!-- 리뷰 -->
          <div class="review">
            <p class="content">{{ reviewStore.reviewRoad[idx].content }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useReviewRoadStore } from "@/stores/customer/review/reviewRoad";
export default {
  setup() {
    const reviewStore = useReviewRoadStore();

    reviewStore.getReview();

    let list = 0;
    list = reviewStore.reviewRoad;

    return {
      reviewStore,
      list,
    };
  },
};
</script>

<style scoped>
.scroll {
  overflow-y: auto;
  height: 55%;

  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  display: flex;
  flex-direction: column;
}
.scroll::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
/* card style start */
.cardContainer {
  display: flex;
  justify-content: center;
  margin-top: 0.7rem;
  margin-bottom: 0.7rem;
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
  height: 60%;
}
.detail {
  height: 40%;
}
hr {
  width: 100%;
  color: yellow;
}
.writer {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  align-items: center;
  height: 25%;
}
.review {
  height: 75%;
  margin-top: 0.5rem;
  padding-left: 1rem;
  padding-right: 1rem;
}
.content {
  overflow: auto;
  height: auto;
}
/* cart style end */
</style>