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
    const positionData = {
      address: null,
      latitude: null,
      longitude: null,
    };
    const newMenuData = {
      name: null,
      price: null,
      img: null,
      description: null,
    };
    const newMenuDataList = [
      {
        name: null,
        price: null,
        img: null,
        description: null,
      },
    ];
    const createImgUrl = null;
    const createImgUrlList = [];
    const myTypeData = {
      modalView: false,
      newMenuIndex: 0,
    };
    return {
      myData,
      newMenuData,
      myTypeData,
      newMenuDataList,
      createImgUrl,
      createImgUrlList,
      positionData, //예제
    };
  },
  actions: {
    updateNewMenu() {
      //초기화
      URL.revokeObjectURL(this.createImgUrl);
      this.createImgUrlList.forEach(function (item) {
        URL.revokeObjectURL(item);
      });
      location.reload();
    },
    registerFoodTruck() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.registerFoodTruck(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateFoodTruck() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.updateFoodTruck(),
        method: "put",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getMyFoodTruck() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.getFoodTruck(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getNearFoodTruck() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.getNearFoodTruck(),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          alert(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setImg() {
      var formData = new FormData();
      console.log(this.myData.truckImg);
      formData.append("file", this.myData.truckImg);
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.setImg(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: formData,
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getImg() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.getImg(1),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          this.set_img(res.data)
          console.log(res)
          

        })
        .catch((err) => {
          console.log(err);
        });
    },
    set_img(file) {
      if (this.createImgUrl !== null) {
        URL.revokeObjectURL(this.createImgUrl);
      }

      var imgsrc = "data:image/png;base64," + window.btoa(String.fromCharCode.apply(null, new Uint8Array(file)));
      console.log('디코드',window.btoa(null, new Uint8Array([file])))
      let e = document.getElementById('my-truck-img')
  
      this.myData.truckImg = imgsrc;
      e.nextElementSibling.src = imgsrc;
      e.nextElementSibling.classList.remove("imgVisible");
      e.nextElementSibling.nextElementSibling.classList.add(
        "imgVisible"
      );
    }
  },
});
