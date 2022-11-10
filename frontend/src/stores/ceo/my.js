import RF from "@/api/RF";
import axios from "axios";
import { defineStore } from "pinia";

export const useCeoMyStore = defineStore("CeoMy", {
  state: () => {
    const myData = {
      truckImg: "",
      truckName: "",
      callNumber: "",
      truckPosition: "",
      openTime: "",
      closeTime: "",
    };
    const newMenuData = {
      name: null,
      price: null,
      img: null,
      description: null,
    };
    const newMenuDataList = [];
    const createImgUrl = null;
    const createImgUrlList = [];
    const myTypeData = {
      modalView: false,
    };
    return {
      myData,
      newMenuData,
      myTypeData,
      newMenuDataList,
      createImgUrl,
      createImgUrlList,
    };
  },
  actions: {
    registerFoodTruck() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.foodtruck.registerFoodTruck(),
        method: "post",
        headers: { Authorization: "Bearer" + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catc((err) => {
          console.log(err);
        });
    },
    updateFoodTruck() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.foodtruck.updateFoodTruck(),
        method: "put",
        headers: { Authorization: "Bearer" + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catc((err) => {
          console.log(err);
        });
    },
    getMyFoodTruck() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.foodtruck.getFoodTruck(),
        method: "get",
        headers: { Authorization: "Bearer" + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catc((err) => {
          console.log(err);
        });
    },
    getNearFoodTruck() {
      const token = localStorage.getItem("token");
      axios({
        url: RF.foodtruck.getNearFoodTruck(),
        method: "get",
        headers: { Authorization: "Bearer" + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catc((err) => {
          console.log(err);
        });
    },
  },
});
