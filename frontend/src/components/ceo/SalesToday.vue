<template>
  <div class="TodayView">
    <section class="TodayBox" v-if="salesStore.salesData.length > 0">
      <h1 class="salesTitle">
        <span>최근 매출</span>
        <span>{{
          salesStore.salesData[salesStore.salesTypeData.salesIndex].regDate
        }}</span>
      </h1>
      <br />
      <div style="padding: 0 1rem 0 1rem">
        <div class="salesMenusTitle">
          <div>메뉴</div>
          <div>매출액</div>
        </div>
      </div>
      <div class="TodaySalesBox">
        <div
          class="salesMenus"
          v-for="(menu, index) in salesStore.salesData[
            salesStore.salesTypeData.salesIndex
          ].businessResList"
          :key="index"
        >
          <div>{{ menu.menuRes.name }}</div>
          <div>{{ menu.revenue }}원</div>
        </div>
      </div>
      <div class="TodayTotalBox">
        <div class="TodayTotal">
          총 {{ salesStore.salesTypeData.profit }} 원
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { useCeoSalesStore } from "@/stores/ceo/sales.js";
export default {
  setup() {
    const salesStore = useCeoSalesStore();
    const selectTime = [5, 10, 15, 20, 25];
    return {
      selectTime,
      salesStore,
    };
  },
};
</script>

<style scoped>
h1 {
  font-size: 1rem;
}
.TodayView {
  font-family: "SCoreDream";
}
.TodayBox {
  position: relative;
  box-sizing: border-box;
  width: 90%;
  height: auto;
  margin: 5%;
  padding: 5%;
  border: 4px solid transparent;
  background-color: var(--color-purple-1);
  border-image: linear-gradient(
    45deg,
    var(--color-pink-2) 0%,
    var(--color-purple-2) 100%
  );
  border-image-slice: 1;
  background-origin: border-box;
  background-clip: border-box;
}
.TodayTotalBox {
  position: relative;
  width: 100%;
  height: 1rem;
  margin: 0.5rem;
}
.TodayTotal {
  position: absolute;
  right: 11%;
}
.salesTitle {
  display: flex;
  justify-content: space-between;
  width: 100%;
}
.salesMenusTitle {
  display: flex;
  justify-content: space-between;
  width: 90%;
  margin: 0.5rem;
}
.salesMenus {
  display: flex;
  width: 96%;
  justify-content: space-between;
}
.TodaySalesBox {
  padding: 0 1rem 0 1rem;
  height: 8rem;
  overflow-y: scroll;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.TodaySalesBox::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>