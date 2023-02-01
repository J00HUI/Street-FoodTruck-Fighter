import RF from "@/api/RF";
import axios from "axios";
import { defineStore } from "pinia";
import router from "@/router/index.js";

export const useCeoScheduleStore = defineStore("CeoSchedule", {
  state: () => {
    const backgroundColor = [
      "rgba(255, 99, 132, 0.2)",
      "rgba(54, 162, 235, 0.2)",
      "rgba(255, 206, 86, 0.2)",
      "rgba(75, 192, 192, 0.2)",
      "rgba(153, 102, 255, 0.2)",
      "rgba(255, 159, 64, 0.2)"
    ];
    const borderColor = [
      "rgba(255,99,132,1)",
      "rgba(54, 162, 235, 1)",
      "rgba(255, 206, 86, 1)",
      "rgba(75, 192, 192, 1)",
      "rgba(153, 102, 255, 1)",
      "rgba(255, 159, 64, 1)"
    ];
    const scheduleList = []
    const eventList = []
    const scheduleDateDtoList = [{
      endTime: "00:00",
      startTime: "00:00",
      workingDay: "전체"
    }]

    const scheduleAddForm = {
      address: "",
      latitude: null,
      longitude: null,
      scheduleId: null,
      scheduleDateDtoList: [
      ],
      title: "title"
    }
    const scheduleTypeData = {
      dateIdx: 0,
      is_update: false,
    }
    return {
      backgroundColor,
      borderColor,
      scheduleList,
      eventList,
      scheduleDateDtoList,
      scheduleAddForm,
      viewToggle: false,
      scheduleTypeData,
    }
  },
  actions: {
    setSchedule() {
      let form = this.scheduleAddForm
      form.scheduleDateDtoList = this.scheduleDateDtoList.slice(1)
      const token = localStorage.getItem("accessToken");
      if (form.address === "" || form.latitude === null || form.longitude === null || form.title === "title" || form.title === "") {
        alert("모든 정보를 입력해주세요")
      } else {
        axios({
          url: RF.schedule.setSchedule(),
          method: "post",
          headers: { Authorization: "Bearer " + token },
          data: this.scheduleAddForm
        })
        .then(() => {
          router.push('/schedule')
        })
        .catch((err) => {
          console.log(err);
        });
      }

    },
    getSchedule() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.schedule.getSchedule(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          let colorIndex = null
          this.scheduleList = res.data
          let backgroundColor = this.backgroundColor
          let borderColor = this.borderColor
          let eventList = this.eventList
          this.scheduleList.forEach(function (item, index) {
            colorIndex = Math.floor(Math.random() * 5);
            const newEvent = {
              id: item.scheduleId,
              listIndex: index,
              title: item.title,
              start: item.scheduleDateDtoList[0].workingDay,
              end: item.scheduleDateDtoList[item.scheduleDateDtoList.length - 1].workingDay,
              allDay: true,

              backgroundColor: backgroundColor[colorIndex],
              borderColor: borderColor[colorIndex],
              textColor: borderColor[colorIndex]
            }
            eventList.push(newEvent)
          })
        })
        .catch((err) => {
          console.log(err);

        });
    },
    deleteSchedule(scheduleId) {

      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.schedule.cancelSchedule(scheduleId),
        method: "patch",
        headers: { Authorization: "Bearer " + token },
      })
        .then(() => {
          router.push('/schedule')
        })
        .catch(() => {
          alert('삭제에 실패했습니다.')
        });
    }
  }
})

