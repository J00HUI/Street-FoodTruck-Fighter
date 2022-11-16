import { defineStore } from "pinia";
import axios from "axios";
import RF from "@/api/RF.js";

export const useStoreDetail = defineStore("storeDetail", {
  state: () => {
    const aboutStore = {
      data: {
        message: "",
        menuList: [
          {
            name: "",
            price: 0,
            description: "",
          },
        ],
        name: "",
        category: "",
        phone: "",
        description: "",
        workingDate: "",
        startTime: "",
        endTime: "",
        title: "",
        groupId: 0,
        is_valid: true,
        latitude: 0,
        longitude: 0,
        address: "",
        grade: 0,
        numberOfPeople: 0,
        time: 0,
        src: null,
      },
    };
    const imgSet = {
      img: null
    }
    const reviews = {
      reviewImg: "",
      reviewer: "",
      grade: 0,
      reviewDate: null,
      content: "",
    };
    return {
      imgSet,
      amount: 0,
      aboutStore,
      reviews,
    };
  },
  actions: {
    getStoreInfo() {
      const foodtruck_id = 2;

      axios({
        url: RF.foodtruck.getFoodTruck(foodtruck_id),
        method: "get",
      })
        .then((res) => {
          // console.log(RF.foodtruck.getFoodTruck(foodtruck_id) + '   getFoodTruck')
          // console.log(JSON.stringify(res.data) + ' res.data');
          this.aboutStore.data = res.data;
          // console.log('here '+ JSON.stringify(this.aboutStore.data))
          var imgsrc =
            "data:image/png;base64," + res.data.src
              this.imgSet.img = imgsrc
              this.imgSet.img = imgsrc


          // console.log(JSON.stringify(this.aboutStore) + ' aboutStore.data');
        })
        .catch(() => {
          console.log("error");
        });
    },
  },
});
