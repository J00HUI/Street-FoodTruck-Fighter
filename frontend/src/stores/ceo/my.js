import RF from "@/api/RF";
import axios from "axios";
import { defineStore } from "pinia";

export const useCeoMyStore = defineStore("CeoMy", {
  state: () => {
    const myData = {
      name: "",
      category: "",
      phone: "",
      description: "string",
      address: "",
      latitude: 0,
      longitude: 0,
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






    registerFoodTruck() {
      console.log(this.myData)
      let formData = new FormData()
      let blob = new Blob([JSON.stringify(this.myData)], { type: "application/json" });
      formData.append('file', this.myTypeData.truckImg)
      formData.append("data", blob)
      for (var key of formData.keys()) {
        console.log(key);
   }
               
   // FormData의 value 확인
   for (var value of formData.values()) {
        console.log(value);
   }
      const token = localStorage.getItem("accessToken");

      axios.post(RF.foodtruck.registerFoodTruck(), formData, {
        headers: {
          Authorization: "Bearer " + token,
          'Content-Type': 'multipart/form-data'
        }
      })
        .then((res) => {
          console.log(res)

        })
        .catch((err) => {
          console.log(err)
        })





      //   axios({
      //     url: RF.foodtruck.registerFoodTruck(),
      //     method: "post",
      //     headers: {
      //       Authorization: "Bearer " + token,
      //       'Content-Type': 'multipart/formdata',
      //     },
      //     data: formData,
      //   })
      //     .then((res) => {
      //       console.log(res)
      //     })
      //     .catch((err) => {
      //       console.log(err);
      //     });
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
