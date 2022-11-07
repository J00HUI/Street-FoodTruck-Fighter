<template>
  <div style="position:relative; width:100%; height:100%">
    <CeoHeader></CeoHeader>
    <nav style="display:flex; height:8%">
      <div class="salesNav">
        <span class="underLine">오늘의 매출</span>
      </div>
      <div class="salesNav salesNavR">
        <span>매출 통계</span>
      </div>
    </nav>
    <Today></Today>
    <div>
      <canvas id="myChart"></canvas>
    </div>

    <Footer></Footer>
  </div>
</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import Today from "@/components/ceo/SalesToday.vue";
import Footer from "@/components/ceo/CeoFooter.vue";
export default {
  components: {
    CeoHeader,
    Today,
    Footer
  },
    
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
</style>