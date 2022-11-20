<template>
  <div style="position:relative; width:100%; height:100%">
    <CeoHeader></CeoHeader>
    <nav style="display:flex; height:8%">
      <div class="salesNav" @click="toDay">
        <span id="sales-today" class="underLine">오늘의 매출</span>
      </div>
      <div class="salesNav salesNavR" @click="allDay">
        <span id="sales-allday">매출 통계</span>
      </div>
    </nav>
    <div class="salesContent" v-if="!salesStore.salesTypeData.viewToggle">
      <Today></Today>
      <Chart v-if="salesStore.salesTypeData.is_chart"></Chart>
    </div>
    <div class="salesContent" v-if="salesStore.salesTypeData.viewToggle">
      <Calendar></Calendar>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { useCeoSalesStore } from "@/stores/ceo/sales.js";
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import Today from "@/components/ceo/SalesToday.vue";
import Chart from "@/components/ceo/SalesGraph.vue";
import Calendar from "@/components/ceo/SalesComparisonCalender.vue";
import Footer from "@/components/ceo/CeoFooter.vue";

export default {
  components: {
    CeoHeader,
    Today,
    Chart,
    Calendar,
    Footer
  },
  setup() {
    const salesStore = useCeoSalesStore();
    salesStore.salesTypeData.viewToggle = false
    salesStore.salesTypeData.is_chart = false
    salesStore.getStatistics()
    salesStore.chartNameData = [];
    salesStore.chartNumData = [];
    function toDay() {
      document.getElementById("sales-today").classList.add("underLine");
      document.getElementById("sales-allday").classList.remove("underLine");
      salesStore.salesTypeData.viewToggle = false;
    }
    function allDay() {
      document.getElementById("sales-today").classList.remove("underLine");
      document.getElementById("sales-allday").classList.add("underLine");
      salesStore.salesTypeData.viewToggle = true;
    }
    return {
      salesStore,
      toDay,
      allDay
    };
  }
};
</script>

<style>
.salesNav {
  display: flex;
  justify-content: center;
  align-items: center;
  font: 1rem "SCoreDream";
  width: 50%;
  color: var(--color-gray-2);
  padding: auto;
  border: 0.02rem solid var(--color-gray-2);
  border-left: none;
}
.salesNavR {
  border-right: none;
}
.underLine {
  border-bottom: 0.2rem solid black;
  color: black;
}
.salesContent {
  display: flex;
  flex-direction: column;
  margin-top: 2rem;
  height: 80%;
}
</style>