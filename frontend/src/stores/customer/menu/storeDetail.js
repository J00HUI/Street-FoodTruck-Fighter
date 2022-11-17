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
    const menuDetail = {
    }
    return {
      imgSet,
      amount: 1,
      aboutStore,
      reviews,
      menuDetail,
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
          console.log(JSON.stringify(res.data) + ' res.data');
          this.aboutStore.data = res.data;
          // this.menuList.idx = this.aboutStore.data.menuList.length
          // console.log('storeDetail.js aboutStore.data '+ JSON.stringify(this.aboutStore.data))
          var imgsrc =
            "data:image/png;base64," + res.data.src
              this.imgSet.img = imgsrc
              this.imgSet.img = imgsrc


          // console.log(JSON.stringify(this.aboutStore.data.grade) + ' aboutStore.data');
          let star = this.aboutStore.data.grade

          if (star >= 0 && star < 2) {
            document.getElementById("rate5").checked = true
          }else if(star>=2 && star<3){
            document.getElementById("rate4").checked = true
      
          }else if(star>=3 && star<4){
            document.getElementById("rate3").checked = true
          }else if(star>=4 && star<5){
            document.getElementById("rate2").checked = true
          }else if(star>=5){
            document.getElementById("rate1").checked = true
          }
        })
        .catch(() => {
          console.log("error");
        });
    },
  },
});
