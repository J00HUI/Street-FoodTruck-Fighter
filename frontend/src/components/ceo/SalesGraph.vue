<template>
  <Bar
    :chart-options="chartOptions"
    :chart-data="chartData"
    :chart-id="chartId"
    :dataset-id-key="datasetIdKey"
    :plugins="plugins"
    :css-classes="cssClasses"
    :styles="styles"
    :width="width"
    :height="height"
  />
</template>

<script>
import { Bar } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);
import { useCeoSalesStore } from "@/stores/ceo/sales.js";
export default {
  name: "BarChart",
  components: { Bar },

  setup() {
    const salesStore = useCeoSalesStore();
    const chartId = {
      type: String,
      default: "bar-chart",
    };
    const datasetIdKey = {
      type: String,
      default: "label",
    };
    const width = {
      type: Number,
      default: 200,
    };
    const height = {
      type: Number,
      default: 400,
    };
    const cssClasses = {
      default: "",
      type: String,
    };
    const type = { Object, default: () => {} };
    const plugins = {
      type: Object,
      default: () => {},
    };
    const chartData = {
      labels: salesStore.chartNameData,
      datasets: [
        {
          label: "판매량/메뉴",
          
          data: salesStore.chartNumData,
          backgroundColor: [
            "rgba(255, 99, 132, 0.2)",
            "rgba(54, 162, 235, 0.2)",
            "rgba(255, 206, 86, 0.2)",
            "rgba(75, 192, 192, 0.2)",
            "rgba(153, 102, 255, 0.2)",
            "rgba(255, 159, 64, 0.2)",
            "rgba(255, 99, 132, 0.2)",
            "rgba(54, 162, 235, 0.2)",
            "rgba(255, 206, 86, 0.2)",
            "rgba(75, 192, 192, 0.2)",
            "rgba(153, 102, 255, 0.2)",
            "rgba(255, 159, 64, 0.2)",
          ],
          borderColor: [
            "rgba(255,99,132,1)",
            "rgba(54, 162, 235, 1)",
            "rgba(255, 206, 86, 1)",
            "rgba(75, 192, 192, 1)",
            "rgba(153, 102, 255, 1)",
            "rgba(255, 159, 64, 1)",
            "rgba(255,99,132,1)",
            "rgba(54, 162, 235, 1)",
            "rgba(255, 206, 86, 1)",
            "rgba(75, 192, 192, 1)",
            "rgba(153, 102, 255, 1)",
            "rgba(255, 159, 64, 1)",
          ],
          borderWidth: 1,
        },
      ],
    };
    const chartOptions = {
      responsive: true,
    };
    return {
      chartId,
      datasetIdKey,
      width,
      height,
      cssClasses,
      type,
      plugins,
      chartData,
      chartOptions,
    };
  },
};
</script>
<style>
</style>