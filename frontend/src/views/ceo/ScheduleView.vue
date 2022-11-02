<template>
  <CeoHeader></CeoHeader>
  <Calendar></Calendar>
  <!-- <Update></Update> -->
</template>

<script>
import CeoHeader from "@/components/ceo/CeoHeader.vue";
import Calendar from "@/components/ceo/ScheduleCalendar.vue";
// import Update from "@/components/ceo/ScheduleUpdate.vue";
export default {
  components: {
    CeoHeader,
    Calendar,

    // Update,
  },
};
</script>

<style>
</style>