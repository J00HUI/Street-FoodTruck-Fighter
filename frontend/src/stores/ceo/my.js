import RF from "@/api/RF";
import axios from "axios";
import { defineStore } from "pinia";

export const useCeoMyStore = defineStore("CeoMy", {
  state: () => {
    const myData = {
      address: "",
      category: "",
      description: "string",
      latitude: 0,
      longitude: 0,
      name: "",
      phone: "",
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
      myCategoryIndex: 0,
      truckImg: null,
      is_update: false,
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
    updateNewMenu() {
      //초기화
      URL.revokeObjectURL(this.createImgUrl);
      this.createImgUrlList.forEach(function (item) {
        URL.revokeObjectURL(item);
      });
      location.reload();
    },


    setFoodTruck() {
      // let formData = new FormData()
      // formData.append('file', this.myTypeData.truckImg)
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.registerFoodTruck(),
        method: "post",
        headers: {
          Authorization: "Bearer " + token,

        },
        data: this.myData,
      })
        .then(() => {

          this.setImg()
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateFoodTruck() {
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.updateFoodTruck(),
        method: "patch",
        headers: { Authorization: "Bearer " + token },
        data:this.myData,
      })
        .then(() => {

          this.setImg()
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getMyFoodTruck() {
      const token = localStorage.getItem("accessToken");
      const truckId = sessionStorage.getItem("foodTruck")
      axios({
        url: RF.foodtruck.getFoodTruck(truckId),
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          this.myTypeData.is_update = true
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setImg() {
      var formData = new FormData();
      console.log(this.myData.truckImg);
      formData.append("file", this.myTypeData.truckImg);
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.setImg(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: formData,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getImg() {
      const truckId = sessionStorage.getItem("foodTruck")
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.getImg(truckId),
        responseType: 'blob',
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log('가져와줘잉')
          console.log(res.data)
          if (res.data !== null) {
            this.set_img(res)
          }


        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 아래 함수 임의 사용금지
    set_img(res) {

      if (this.createImgUrl !== null) {
        URL.revokeObjectURL(this.createImgUrl);
      }

      let imgTag = document.getElementById('my-truck-img')
      const url = URL.createObjectURL(new Blob([res.data], { type: res.headers['content-type'] }));
      // this.myData.truckImg = file;
      this.createImgUrl = url
      imgTag.nextElementSibling.src = url;
      imgTag.nextElementSibling.classList.remove("imgVisible");
      imgTag.nextElementSibling.nextElementSibling.classList.add(
        "imgVisible"
      );
    }
  },
});
