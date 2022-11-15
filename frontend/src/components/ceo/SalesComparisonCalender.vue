<template>
  <FullCalendar :options="options"></FullCalendar>
</template>

<script setup>
import router from "@/router";
import { reactive, ref } from "vue";
import "@fullcalendar/core/vdom";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import listPlugin from "@fullcalendar/list";
import interactionPlugin from "@fullcalendar/interaction";

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

const eventList = [];

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

    let title = "?";


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
    console.log(e)
    // for (let str = e.event.start; str < end; str.setDate(str.getDate() + 1)) {

    // }

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
