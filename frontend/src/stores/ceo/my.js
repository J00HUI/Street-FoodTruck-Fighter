import { defineStore } from "pinia";

export const useCeoMyStore = defineStore("CeoMy", {

  state: () => {
    const myData = {
      truckImg: '',
      truckName: '',
      callNumber: '',
      truckPosition: '',
      openTime:'',
      closeTime:'',
    }
    return {
      myData,


    }
  },
  actions: () => {

  }
})