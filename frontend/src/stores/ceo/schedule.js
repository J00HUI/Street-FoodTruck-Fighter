import RF from "@/api/RF";
import axios from "axios";
import { defineStore } from "pinia";

export const useCeoScheduleStore = defineStore("CeoSchedule", {
  state: () => {

    const scheduleAddForm = {
      address: null,
      latitude: null,
      longitude: null,
      scheduleDateDtoList: [{endTime:"00:00",startTime:"00:00",workingDay:"2022-11-15"},
      {endTime:"00:00",startTime:"00:00",workingDay:"2022-11-20"}
      ],
      title: "string"
    }
    return {
      scheduleAddForm,
      viewToggle: false,
    }
  },
  actions: {
    setSchedule() {
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
          console.log(res)
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }
})

