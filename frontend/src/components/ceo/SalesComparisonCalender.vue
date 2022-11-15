<template>
  <FullCalendar :options="options"></FullCalendar>
</template>

<script setup>
import router from "@/router";
import { useCeoScheduleStore } from "@/stores/ceo/schedule.js";
import { reactive, ref } from "vue";
import "@fullcalendar/core/vdom";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import listPlugin from "@fullcalendar/list";
import interactionPlugin from "@fullcalendar/interaction";

const store = useCeoScheduleStore();
const id = ref(0);
let colorIndex = Math.floor(Math.random() * 6);
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
// let colorList = ["yellow", "orange", "purple", "blue", "pink", "green"];
const eventList = [];
let eventsData = store.scheduleAddForm.scheduleDateDtoList;
if (eventsData.length > 0) {
  eventList.push({
    title: store.scheduleAddForm.title,
    start: eventsData[0].workingDay,
    end: eventsData[eventsData.length - 1].workingDay,
    // backgroundColor: "var(--color-" + colorList[colorIndex] + "-1)",
    // borderColor: "var(--color-" + colorList[colorIndex] + "-2)"
    backgroundColor: backgroundColor[colorIndex],
    borderColor: borderColor[colorIndex],
    textColor: borderColor[colorIndex]
  });
}

const options = reactive({
  plugins: [dayGridPlugin, timeGridPlugin, listPlugin, interactionPlugin],
  initialView: "dayGridMonth",
  headerToolbar: {
    left: "prev",
    center: "title",
    right: "next today"
  },
  editable: true,
  selectable: true,
  weekends: true,
  dayMaxEvents: 3,
  eventMaxStack: 99,
  longPressDelay: 300,
  eventLongPressDelay: 300,
  selectLongPressDelay: 300,
  events: eventList,
  select: arg => {
    id.value = id.value + 1;
    const cal = arg.view.calendar;
    // cal.unselect();
    let title = "?";
    // title = arg.startStr.slice(-5);

    colorIndex = Math.floor(Math.random() * 6);
    cal.addEvent({
      id: `${id.value}`,
      title: title,
      start: arg.start,
      end: arg.end,
      allDay: true,

      backgroundColor: backgroundColor[colorIndex],
      borderColor: borderColor[colorIndex],
      textColor: borderColor[colorIndex]
    });
  },
  eventClick: e => {
    let end = e.event.end;
    for (let str = e.event.start; str < end; str.setDate(str.getDate() + 1)) {
      const scheduleDateDto = {
        endTime: "00:00",
        startTime: "00:00",
        workingDay: null
      };
      scheduleDateDto.workingDay = `${str.getFullYear()}-${str.getMonth()}-${str.getDate()}`;

      store.scheduleDateDtoList.push(scheduleDateDto);
      console.log(scheduleDateDto.workingDay);
    }
    store.scheduleAddForm.title = e.event.title;

    router.push("/scheduleupdate");
  },
  // eventMouseEnter: arg => {

  //   console.log(arg.event.taget);
  // },

  titleFormat: function(date) {
    return `${date.date.year}년 ${date.date.month + 1}월`;
  },

  eventTimeFormat: {
    // like '14:30:00'
    hour: "2-digit",
    minute: "2-digit",
    second: "2-digit",
    meridiem: false
  }
});
</script>

<style>
.fc-toolbar {
  justify-content: space-around !important;
}
.fc {
  height: 80% !important;
}
.fc-button {
  background-color: var(--color-yellow-2) !important;
  border: none !important;
}
.fc-day-sat {
  color: blue !important;
}
.fc-day-sun {
  color: red !important;
}
.fc-event-title.fc-sticky {
  white-space: normal;
}
.fc-daygrid-more-link {
  font-size: 0.25rem;
}
.fc-h-event {
  border: 2px solid;
  color: black;
  /* text-shadow: 1px 0px black, -1px 0px black, 0px -1px black, 0px 1px black, 1px 1px black, -1px 1px black, 1px -1px black, -1px -1px black; */
}
</style>
