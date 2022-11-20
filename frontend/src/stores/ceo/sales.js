import { defineStore } from "pinia";
import RF from "@/api/RF";
import axios from "axios";
import router from "@/router/index.js";
export const useCeoSalesStore = defineStore("CeoSales", {
  state: () => {
    const salesData = []
    const chartNameData = []
    const chartNumData = []
    const eventList = []
    const salesTypeData = {
      viewToggle: false,
      addEventIdx: 0,
      is_chart: false,
      profit: 0,
      salesIndex:0,
    }
    return {
      salesTypeData,
      salesData,
      chartNameData,
      chartNumData,
      eventList
    }
  },
  actions: {
    getStatistics() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.business.businessPath(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          this.salesData = res.data

          const max_j = this.salesData.length
          for (let j = 0; j < max_j; j++) {
            const newEvent = {
              listIndex: j,
              title: this.salesData[j].regDate,
              start: this.salesData[j].regDate,
              end: this.salesData[j].regDate,
              allDay: true,
              display: 'background',
              backgroundColor: "rgba(255, 99, 132, 0.2)",
            }
            
            this.eventList.push(newEvent)
          }
          this.makeSalesData()

        })
        .catch((err) => {
          console.log(err);
        });
    },
    makeSalesData() {
      this.salesTypeData.is_chart = false
      this.salesTypeData.profit = 0
      this.chartNameData = []
      this.chartNumData = []
      const index = this.salesTypeData.salesIndex
      const dataList = this.salesData[index].businessResList
      const max_i = dataList.length
      for (let i = 0; i < max_i; i++) {
        this.chartNameData.push(dataList[i].menuRes.name)
        this.chartNumData.push(dataList[i].revenue / dataList[i].menuRes.price)
        this.salesTypeData.profit += dataList[i].revenue
      }
      this.salesTypeData.is_chart = true

    },
    setStatistics() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.business.businessPath(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: this.scheduleAddForm
      })
        .then(() => {
          router.push('/salesstatistics')
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }
})
