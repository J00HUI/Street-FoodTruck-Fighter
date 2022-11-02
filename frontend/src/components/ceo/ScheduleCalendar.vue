<template>
  <FullCalendar :options="options"></FullCalendar>
</template>
<script setup>
import { reactive, ref, watch } from "vue";
import "@fullcalendar/core/vdom";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import listPlugin from "@fullcalendar/list";
import interactionPlugin from "@fullcalendar/interaction";
import useEvents from '@/assets/ceo/schedule.js'

const id = ref(10)

const { getEvents, createEvent, updateEvent, deleteEvent } = useEvents()

const options = reactive({
  plugins: [dayGridPlugin, timeGridPlugin, listPlugin, interactionPlugin],
  initialView: "dayGridMonth",
  
  headerToolvar: {
    left: 'prev, next today',
    center: 'title',
    right: 'dayGridMonth, dayGridWeek, listDay',
  },
  editable: true,
  selectable: true,
  weekends:true,

  select: (arg) => {
    console.log( arg)
    id.value = id.value + 1
    const cal = arg.view.calendar
    cal.unselect()
    cal.addEvent({
      id: `${id.value}`,
      title: `New event ${id.value}`,
      start: arg.start,
      end: arg.end,
      allDay:true,
    })
  },
  eventClick: (arg) => {
    console.log(arg.event.title)
  },
  events: [],
  eventAdd: (arg) => {
    createEvent({
      id:arg.event.id,
      title:arg.event.start,
      start: arg.event.start,
      end: arg.event.end,
      allDay:arg.event.allDay
    })
  },
    eventChange: (arg) => {
    updateEvent({
      id:arg.event.id,
      title:arg.event.start,
      start: arg.event.start,
      end: arg.event.end,
      allDay:arg.event.allDay
    })
  },
    eventRemove: (arg) => {
    deleteEvent({
      id:arg.event.id,
      title:arg.event.start,
      start: arg.event.start,
      end: arg.event.end,
      allDay:arg.event.allDay
    })
  },

})
  options.events = getEvents.value
  watch(getEvents, () => {
   options.events = getEvents.value
  })
</script>

<style>
</style>
