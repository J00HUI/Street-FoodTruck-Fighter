import { defineStore } from "pinia";

export const useCeoScheduleStore = defineStore("CeoSchedule", {
  state: () => {

    return {
      viewToggle: false,
    }
  }
})

