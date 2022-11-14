import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const useStoreDetail = defineStore("storeDetail", {
  state: () => {
    const aboutStore = {
      data: {
        address: "",
        category: "",
        description: "",
        endTime: null,
        grade: 0,
        is_valid: true,
        latitude: 0.0,
        longtitue: 0.0,
        menuList: [
          {
            id: 0,
            name: "",
            price: 0,
            description: "",
            src: 1,
          },
        ],
        name: "",
        numberOfPeople: 0,
        phone: "",
        src: null,
        startTime: null,
        time: 0,
        workingDate: "",
      },
    };
    // const aboutStore = {
    //   data :{
    //     name : '',
    //   }
    // }
    // const menuList = [{
    //   id: 0,
    //   name: "",
    //   price: 0,
    //   description: "",
    //   src: null,
    // }];
    const reviews = {
      reviewImg: "",
      reviewer: "",
      grade: 0,
      reviewDate: null,
      content: "",
    };
    return {
      amount: 0,
      aboutStore,
      // menuList,
      reviews,
    };
  },
  actions: {
    getStoreInfo() {
      const foodtruck_id = 1;

      axios({
        url: RF.foodtruck.getFoodTruck(foodtruck_id),
        method: "get",
      })
        .then((res) => {
          console.log(RF.foodtruck.getFoodTruck(foodtruck_id))
          console.log(res.JSON + ' res.data');
          this.aboutStore = res.data;
          console.log(this.aboutStore.data + ' aboutStore.data');
        })
        .catch(() => {
          console.log("error");
        });
    },
  },
});
