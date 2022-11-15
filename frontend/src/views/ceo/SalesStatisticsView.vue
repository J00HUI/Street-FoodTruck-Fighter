<template>
  <div style="position:relative; width:100%; height:100%">
    <CeoHeader></CeoHeader>
    <nav style="display:flex; height:8%">
      <div class="salesNav" @click="toDay">
        <span id="sales-today" class="underLine">오늘의 매출</span>
      </div>
      <div class="salesNav salesNavR" @click="allDay">
        <span id="sales-allday" class="">매출 통계</span>
      </div>
    </nav>
    <div class="todayView">
      <Today></Today>
      <Chart></Chart>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import Today from "@/components/ceo/SalesToday.vue";
import Chart from "@/components/ceo/SalesGraph.vue";
import Footer from "@/components/ceo/CeoFooter.vue";
export default {
  components: {
    CeoHeader,
    Today,
    Chart,
    Footer
  },
  setup() {
    // const todayE = document.getElementById('sales-today')
    // const alldayE = document.getElementById('sales-allday')
    // function toDay() {
    //   console.log(todayE)
    //   todayE.classList.add('underLine')

    //   alldayE.classList.remove('underLine')
    // }
    // function allDay() {
    //   todayE.classList.add('underLine')
    //   alldayE.classList.remove('underLine')
    // }
    return {
      // toDay,
      // allDay
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
</style>