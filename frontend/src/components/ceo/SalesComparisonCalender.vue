<template>
  <FullCalendar :options="options"></FullCalendar>
</template>

<script setup>
import { reactive} from "vue";
import "@fullcalendar/core/vdom";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import listPlugin from "@fullcalendar/list";
import interactionPlugin from "@fullcalendar/interaction";
import { useCeoSalesStore } from "@/stores/ceo/sales";
const salesStore = useCeoSalesStore();


// const backColorList = [rgb(72, 131, 203),rgb(203, 72, 72)]

const options = reactive({
  plugins: [dayGridPlugin, timeGridPlugin, listPlugin, interactionPlugin],
  initialView: "dayGridMonth",
  headerToolbar: {
    left: "prev",
    center: "title",
    right: "next today"
  },
  editable: true,
  selectable: false,
  weekends: true,
  dayMaxEvents: 1,
  eventMaxStack: 99,
  longPressDelay: 300,
  eventLongPressDelay: 300,
  selectLongPressDelay: 300,
  events: salesStore.eventList,

  eventClick: e => {
    salesStore.salesTypeData.salesIndex = e.event.extendedProps['listIndex']
    salesStore.salesTypeData.is_chart = false
    salesStore.makeSalesData()
    salesStore.salesTypeData.viewToggle = false

  },


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
  color: rgb(203, 72, 72);
  /* text-shadow: 1px 0px black, -1px 0px black, 0px -1px black, 0px 1px black, 1px 1px black, -1px 1px black, 1px -1px black, -1px -1px black; */
}

</style>
