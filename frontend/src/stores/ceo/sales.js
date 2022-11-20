import { defineStore } from "pinia";
import RF from "@/api/RF";
import axios from "axios";

export const useCeoSalesStore = defineStore("CeoSales", {
  state: () => {
    const salesData = []
    const chartNameData = []
    const chartNumData = []
    const eventList = {}
    const salesTypeData = {
      viewToggle: false,
      addEventIdx: 0,
      is_chart: false,
      profit:0,
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
          const dataList = this.salesData[this.salesData.length - 1].businessResList
          const max_i = dataList.length
          console.log(dataList)
          // const newEvent = {
          //   id: item.scheduleId,
          //   listIndex: index,
          //   title: item.title,
          //   start: item.scheduleDateDtoList[0].workingDay,
          //   end: item.scheduleDateDtoList[item.scheduleDateDtoList.length - 1].workingDay,
          //   allDay: true,

          //   backgroundColor: backgroundColor[colorIndex],
          //   borderColor: borderColor[colorIndex],
          //   textColor: borderColor[colorIndex]
          // }
          for (let i = 0; i < max_i; i++) {
            this.chartNameData.push(dataList[i].menuRes.name)
            this.chartNumData.push(dataList[i].revenue / dataList[i].menuRes.price)
            this.salesTypeData.profit += dataList[i].revenue
          }
          this.salesTypeData.is_chart = true
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setStatistics() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.business.businessPath(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: this.scheduleAddForm
      })
        .then((res) => {

          console.log(res.data)
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }
})
