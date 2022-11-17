import RF from "@/api/RF";
import axios from "axios";
import { defineStore } from "pinia";

export const useCeoMyStore = defineStore("CeoMy", {
  state: () => {
    const myData = {
      address: "",
      category: "",
      dateDtoList: [],
      description: "string",
      latitude: 0,
      longitude: 0,
      truckPosition: "",
      menuList: [],
      name: "",
      phone: "",

      truckImg: "",

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
      myCategoryIndex:0,
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
      var formData = new FormData()
      formData.append("address", this.myData.address)
      formData.append("category", this.myData.category)
      formData.append("dateDtoList", this.myData.dateDtoList)
      formData.append("description", this.myData.description)
      formData.append("latitude", this.myData.latitude)
      formData.append("longitude", this.myData.longitude)
      formData.append("menuList", this.myData.menuList)
      formData.append("name", this.myData.name)
      formData.append("phone", this.myData.phone)
      
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
        responseType: 'blob',
        method: "get",
        headers: { Authorization: "Bearer " + token },
      })
        .then((res) => {
          console.log(res.data)
          if (res.data !== null) {
            this.set_img(res)
          }


        })
        .catch((err) => {
          console.log(err);
        });
    },
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
