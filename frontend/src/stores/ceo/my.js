import axios from "axios";
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
    getMyFoodTruck() {
      axios({
        url: '',
        method: '',
        headers: ''
      }).then (res => {
        console.log('yes')
      }).catc(err => {
        console.log('no')
      }) 
    }


  }
})