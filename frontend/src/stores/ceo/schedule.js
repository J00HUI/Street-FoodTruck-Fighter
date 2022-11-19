import RF from "@/api/RF";
import axios from "axios";
import { defineStore } from "pinia";

export const useCeoScheduleStore = defineStore("CeoSchedule", {
  state: () => {
    const scheduleList = []
    const scheduleDateDtoList = [{
      endTime: "00:00",
      startTime: "00:00",
      workingDay: "전체"
    }]

    const scheduleAddForm = {
      address: null,
      latitude: null,
      longitude: null,
      scheduleDateDtoList: [
      ],
      title: "+추가"
    }
    const scheduleTypeData = {
      dateIdx: 0
    }
    return {
      scheduleList,
      scheduleDateDtoList,
      scheduleAddForm,
      viewToggle: false,
      scheduleTypeData,
    }
  },
  actions: {
    setSchedule() {
      this.scheduleAddForm.scheduleDateDtoList = this.scheduleDateDtoList.slice(1)
      console.log(this.scheduleAddForm)
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.schedule.setSchedule(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: this.scheduleAddForm
      })
        .then((res) => {
          console.log(res)
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getSchedule() {

      const token = localStorage.getItem("accessToken");
      console.log(token)
      axios({
        url: RF.schedule.getSchedule(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          // let scehdule = {
          //   title  : 'event2',
          //   start  : '2022-11-19',
          //   end    : '2022-11-30',
          // }
          // this.scheduleList.push(scehdule)
          
          // res.data.forEach((item, idx) => {
            
            
            
          // });
          console.log(res.data)
          console.log(this.scheduleList)
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }
})

