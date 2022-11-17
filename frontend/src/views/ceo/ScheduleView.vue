<template>
  <CeoHeader></CeoHeader>
  <Calendar></Calendar>
</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import Calendar from "@/components/ceo/ScheduleCalendar.vue";
import { useCeoScheduleStore } from "@/stores/ceo/schedule.js";
export default {
  components: {
    CeoHeader,
    Calendar
  },
  setup() {
    const scheduleStore = useCeoScheduleStore()
    scheduleStore.getSchedule()
  }
};
</script>

<style>
</style>