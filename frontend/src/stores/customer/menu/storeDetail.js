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
            menuId: "",
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
    const nearData = [{}];
    const imgSet = {
      img: null,
    };
    const reviews = {
      reviewImg: "",
      reviewer: "",
      grade: 0,
      reviewDate: null,
      content: "",
    };
    const menuDetail = {
      foodtruckId: 0,
      menuList: [{ count: 0, menuId: 0 }],
    };
    // const foodtruck_id = sessionStorage.getItem("foodtruckId")
    const foodtruck_id = 1;
    const amount = 1;

    return {
      imgSet,
      amount,
      aboutStore,
      nearData,
      reviews,
      menuDetail,
      foodtruck_id,
    };
  },
  actions: {
    getStoreInfo() {
      axios({
        url: RF.foodtruck.getFoodTruck(this.foodtruck_id),
        method: "get",
      })
        .then((res) => {
          // console.log(RF.foodtruck.getFoodTruck(foodtruck_id) + '   getFoodTruck')
          // console.log(JSON.stringify(res.data) + ' res.data');
          this.aboutStore.data = res.data;
          // this.menuList.idx = this.aboutStore.data.menuList.length
          // console.log('storeDetail.js aboutStore.data '+ JSON.stringify(this.aboutStore.data))
          var imgsrc = "data:image/png;base64," + res.data.src;
          this.imgSet.img = imgsrc;
          this.imgSet.img = imgsrc;

          // console.log(JSON.stringify(this.aboutStore.data.grade) + ' aboutStore.data');
          let star = this.aboutStore.data.grade;

          if (star >= 0 && star < 2) {
            document.getElementById("rate5").checked = true;
          } else if (star >= 2 && star < 3) {
            document.getElementById("rate4").checked = true;
          } else if (star >= 3 && star < 4) {
            document.getElementById("rate3").checked = true;
          } else if (star >= 4 && star < 5) {
            document.getElementById("rate2").checked = true;
          } else if (star >= 5) {
            document.getElementById("rate1").checked = true;
          }
        })
        .catch(() => {
          console.log("error");
        });
    },

    getNearStoreInfo() {
      axios({
        url: RF.foodtruck.getNearFoodTruck(),
        method: "post",
        data: {
          category: sessionStorage.getItem("selectedMenu"),
          lat: sessionStorage.getItem("latitude"),
          lng: sessionStorage.getItem("longitude"),
        },
      })
        .then((res) => {
          console.log(res.data);

          this.nearData = res.data;

          console.log("가까운 푸드트럭 조회 성공");
        })
        .catch(() => {
          console.log("가까운 푸드트럭 조회 실패");
        });
    },
  },
});
