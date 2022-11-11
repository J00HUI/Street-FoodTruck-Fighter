import RF from "@/api/RF";
import axios from "axios";
import { defineStore } from "pinia";

export const useCeoScheduleStore = defineStore("CeoSchedule", {
  state: () => {
    const scheduleDateDto = {
      endTime: "HH:mm",
      startTime: "HH:mm",
      workingDay: "yyyy-MM-dd"
    }
    const scheduleDateDtoList = [

    ]
    const scheduleAddForm = {
      address: null,
      latitude: null,
      longitude: null,
      scheduleDateDtoList: [
        {
          endTime: "HH:mm",
          startTime: "HH:mm",
          workingDay: "yyyy-MM-dd"
        }
      ],
      title: "string"
    }
    return {
      scheduleAddForm,
      viewToggle: false,
      scheduleDateDto,
      scheduleDateDtoList,
    }
  },
  actions: {
    setSchedule() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.schedule.setSchedule(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getSchedule() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.schedule.getSchedule(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
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

