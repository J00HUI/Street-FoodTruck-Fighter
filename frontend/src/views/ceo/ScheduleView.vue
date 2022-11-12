<template>
  <CeoHeader></CeoHeader>
  <Calendar></Calendar>
</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import Calendar from "@/components/ceo/ScheduleCalendar.vue";

export default {
  components: {
    CeoHeader,
    Calendar,
  },
  setup() {

  }
};
</script>

<style>
</style>