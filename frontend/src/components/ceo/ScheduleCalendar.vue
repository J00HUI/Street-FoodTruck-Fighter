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
console.log(store);
const id = ref(0);

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

  select: arg => {
    id.value = id.value + 1;
    const cal = arg.view.calendar;
    console.log(cal.getEvents()[0]);
    // cal.unselect();
    let title = "";
    const startDate = arg.startStr.replace(/-/g, "");
    const endDate = arg.endStr.replace(/-/g, "");
    if (startDate == endDate - 1) {
      title = arg.startStr.slice(-5);
    } else {
      title = `${arg.startStr.slice(-5)} ~ ${endDate.slice(-4, -2) +
        "-" +
        (endDate.slice(-2)<10 ? '0' + (endDate.slice(-1) -  1) : endDate.slice(-2) - 1)}`;
    }
    cal.addEvent({
      id: `${id.value}`,
      title: title,
      start: arg.start,
      end: arg.end,
      allDay: true
    });
  },
  eventClick: () => {
    console.log(this);
    router.push("/scheduleupdate");
  },
  eventMouseEnter: arg => {
    console.log(arg);
    console.log(arg.event.target);
  },

  titleFormat: function(date) {
    return `${date.date.year}년 ${date.date.month + 1}월`;
  },
  events: [],
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
  background-color: var(--color-purple-2) !important;
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
</style>
