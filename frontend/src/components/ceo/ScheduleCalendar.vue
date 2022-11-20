<template>
  <FullCalendar :options="options"></FullCalendar>
</template>

<script setup>
import router from "@/router";
import { useCeoScheduleStore } from "@/stores/ceo/schedule.js";
import { useKakaoStore } from "@/stores/kakao.js";
import { reactive, ref } from "vue";
import "@fullcalendar/core/vdom";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import listPlugin from "@fullcalendar/list";
import interactionPlugin from "@fullcalendar/interaction";

const store = useCeoScheduleStore();
const kakaoStore = useKakaoStore();

kakaoStore.searchTypeData.goBack = true;
//초기화
store.scheduleAddForm = {
  address: null,
  latitude: null,
  longitude: null,
  scheduleDateDtoList: [],
  title: "title",
};
store.scheduleDateDtoList = [
  {
    endTime: "00:00",
    startTime: "00:00",
    workingDay: "전체",
  },
];
store.scheduleTypeData = {
  dateIdx: 0,
  is_update: false,
};

const id = ref(0);
let colorIndex = Math.floor(Math.random() * 6);
store.scheduleList = [];
store.eventList = [];

const options = reactive({
  plugins: [dayGridPlugin, timeGridPlugin, listPlugin, interactionPlugin],
  initialView: "dayGridMonth",
  headerToolbar: {
    left: "prev",
    center: "title",
    right: "next today",
  },
  editable: true,
  selectable: true,
  weekends: true,
  dayMaxEvents: 3,
  eventMaxStack: 99,
  longPressDelay: 300,
  eventLongPressDelay: 300,
  selectLongPressDelay: 300,
  events: store.eventList,
  select: (arg) => {
    id.value = id.value - 1;
    const cal = arg.view.calendar;
    // cal.unselect();
    let title = "title";
    // title = arg.startStr.slice(-5);

    colorIndex = Math.floor(Math.random() * 6);
    cal.addEvent({
      id: `${id.value}`,
      title: title,
      start: arg.start,
      end: arg.end,
      allDay: true,

      backgroundColor: store.backgroundColor[colorIndex],
      borderColor: store.borderColor[colorIndex],
      textColor: store.borderColor[colorIndex],
    });
  },
  eventClick: (e) => {
    kakaoStore.searchTypeData.goBack = false;
    if (e.event.extendedProps["listIndex"] === undefined) {
      let end = e.event.end;
      for (let str = e.event.start; str < end; str.setDate(str.getDate() + 1)) {
        const scheduleDateDto = {
          endTime: "00:00",
          startTime: "00:00",
          workingDay: null,
        };
        // 한국 시간대 설정
        scheduleDateDto.workingDay = new Date(
          str.getTime() - str.getTimezoneOffset() * 60000
        )
          .toISOString()
          .substring(0, 10);

        store.scheduleDateDtoList.push(scheduleDateDto);
      }
      store.scheduleAddForm.title = e.event.title;
    } else {
      let saved_schedule =
        store.scheduleList[e.event.extendedProps["listIndex"]];
      store.scheduleAddForm = saved_schedule;
      store.scheduleDateDtoList.push(...saved_schedule.scheduleDateDtoList);
    }

    router.push("/scheduleupdate");
  },
  // eventMouseEnter: arg => {

  //   console.log(arg.event.taget);
  // },

  titleFormat: function (date) {
    return `${date.date.year}년 ${date.date.month + 1}월`;
  },

  eventTimeFormat: {
    // like '14:30:00'
    hour: "2-digit",
    minute: "2-digit",
    second: "2-digit",
    meridiem: false,
  },
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
.fc-h-event {
  border: 2px solid;
  color: black;
  /* text-shadow: 1px 0px black, -1px 0px black, 0px -1px black, 0px 1px black, 1px 1px black, -1px 1px black, 1px -1px black, -1px -1px black; */
}
</style>
