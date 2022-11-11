import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const useStoreDetail = defineStore("storeDetail", {
  state: () => {
    const storeInfo = {
      storeName: '',
      grade: 0.0,
      wating: 0,
      watingTime: 0,
    };
    const menu = {
      menuName: '',
      price: 0,
    };
    // const aboutStore = {
    //   storeImg: '',
    //   storeName: '',
    //   startTime: null,
    //   endTime: null,
    //   phoneNum: '',
    //   place: '',
    // };
    const aboutStore = []
    const reviews = {
      reviewImg : '',
      reviewer : '',
      grade : 0,
      reviewDate : null,
      content : '',
    }
    return {
      storeInfo,
      menu,
      aboutStore,
      reviews,
    };
  },
  actions: {
    getStoreInfo(){
      console.log('bbb')
      const foodtruck_id = 1
      console.log(RF.foodtruck.getFoodTruck(foodtruck_id))
      // const token = localStorage.getItem('accessToken')
      
      axios({
        url: RF.foodtruck.getFoodTruck(foodtruck_id),
        method: "get",
        // headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log('ccc')
          console.log(res)
          this.aboutStore = res.data
          // console.log(RF.foodtruck.getFoodTruck(foodtruck_id))
        })
        .catch(() => {
          console.log('error')
        });
    }
  }
});
