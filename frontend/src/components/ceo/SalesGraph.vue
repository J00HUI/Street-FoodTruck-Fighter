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
  LinearScale
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  name: "BarChart",
  components: { Bar },

  setup() {
    const chartId = {
      type: String,
      default: "bar-chart"
    };
    const datasetIdKey = {
      type: String,
      default: "label"
    };
    const width = {
      type: Number,
      default: 200
    };
    const height = {
      type: Number,
      default: 400
    };
    const cssClasses = {
      default: "",
      type: String
    };
    const type = { Object, default: () => {} };
    const plugins = {
      type: Object,
      default: () => {}
    };
    const chartData = {
      labels: ["성훈", "태안", "승주"],
      datasets: [{ data: [40, 20, 12] }]
    };
    const chartOptions = {
      responsive: true
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
  }
};
</script>
<style>

</style>