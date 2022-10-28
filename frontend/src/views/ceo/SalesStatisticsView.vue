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
import Chart from "chart.js/auto";
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import Today from "@/components/ceo/SalesToday.vue";
import Footer from "@/components/ceo/CeoFooter.vue";
export default {
  components: {
    CeoHeader,
    Today,
    Footer
  },
  setup() {
    const labels = Utils.months({ count: 7 });
    const data = {
      labels: labels,
      datasets: [
        {
          label: "My First Dataset",
          data: [65, 59, 80, 81, 56, 55, 40],
          backgroundColor: [
            "rgba(255, 99, 132, 0.2)",
            "rgba(255, 159, 64, 0.2)",
            "rgba(255, 205, 86, 0.2)",
            "rgba(75, 192, 192, 0.2)",
            "rgba(54, 162, 235, 0.2)",
            "rgba(153, 102, 255, 0.2)",
            "rgba(201, 203, 207, 0.2)"
          ],
          borderColor: [
            "rgb(255, 99, 132)",
            "rgb(255, 159, 64)",
            "rgb(255, 205, 86)",
            "rgb(75, 192, 192)",
            "rgb(54, 162, 235)",
            "rgb(153, 102, 255)",
            "rgb(201, 203, 207)"
          ],
          borderWidth: 1
        }
      ]
    };
    const config = {
      type: "bar",
      data: data,
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    };
    const chart = new Chart(ctx, {
      type: "line",
      data: data,
      options: {
        onClick: e => {
          const canvasPosition = getRelativePosition(e, chart);

          // Substitute the appropriate scale IDs
          const dataX = chart.scales.x.getValueForPixel(canvasPosition.x);
          const dataY = chart.scales.y.getValueForPixel(canvasPosition.y);
        }
      }
    });
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