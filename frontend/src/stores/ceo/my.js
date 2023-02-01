import RF from "@/api/RF";
import axios from "axios";
import { defineStore } from "pinia";
import blue from "@/assets/ceo/noImgBlue.png"
import green from "@/assets/ceo/noImgGreen.png"
export const useCeoMyStore = defineStore("CeoMy", {
  state: () => {
    const myData = {
      category: "",
      description: "",
      name: "",
      phone: "",
    };

    const newMenuData = {
      name: null,
      price: null,
      description: null,
    };
    const newMenuDataList = [
      {
        name: null,
        price: null,
        description: null,
      },
    ];
    const createImgUrl = null;
    const createMenuImgList = []
    const createMenuImgUrlList = [];
    const myTypeData = {
      modalView: false,
      newMenuIndex: 0,
      savedMenuIndex: 0,
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
      createMenuImgList,
      createMenuImgUrlList,
    };
  },
  actions: {
    updateNewMenu() {
      const token = localStorage.getItem("accessToken");
      const updateList = []
      updateList.push(this.newMenuDataList.slice(0, this.myTypeData.savedMenuIndex))
      console.log(updateList)
      console.log('업데이잉ㅇ이')
      axios({
        url: RF.menu.updateMenu(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: updateList
      })
        .then((res) => {
          console.log(res)
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err);
        });

    },
    setNewMenu() {
      const token = localStorage.getItem("accessToken");
      const menuList = {
        menuReqList: null
      }
      menuList.menuReqList = this.newMenuDataList.slice(this.myTypeData.savedMenuIndex, -1)
      console.log('새로운 메뉴' + menuList)
      if (menuList.menuReqList.length > 0) {
        axios({
          url: RF.menu.setMenu(),
          method: "post",
          headers: { Authorization: "Bearer " + token },
          data: menuList
        })
          .then((res) => {
            console.log(res)
            console.log(res.data)
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    getMenu() {
      const token = localStorage.getItem("accessToken");
      const truckId = sessionStorage.getItem("foodTruck")
      axios({
        url: RF.menu.getMenu(truckId),
        method: "get",
        headers: { Authorization: "Bearer " + token },

      })
        .then((res) => {
          const newData = {
            name: null,
            price: null,
            description: null,
          };
          this.newMenuDataList = res.data
          this.newMenuDataList.push(newData)
          this.myTypeData.newMenuIndex += res.data.length - 1
          this.myTypeData.savedMenuIndex = res.data.length - 1

          let max_i = res.data.length
          for (let i = 0; i < max_i - 1; i++) {
            if (this.newMenuDataList[i].menuId === null) {
              console.log(null)
            } else {
              this.getMenuImg(this.newMenuDataList[i].menuId)
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getMenuImg(menuId) {

      axios({
        url: RF.menu.getMenuImg(menuId),
        method: "get",
      })
        .then((res) => {

          if (res.data === "") {
            this.createMenuImgList.push(null)
            if (this.createMenuImgUrlList.length % 2 === 1) {
              this.createMenuImgUrlList.push(green)
            } else {
              this.createMenuImgUrlList.push(blue)
            }

          }
        })
        .catch((err) => {
          console.log(err);
        });

    },
    setFoodTruck() {
      // let formData = new FormData()
      // formData.append('file', this.myTypeData.truckImg)
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.registerFoodTruck(),
        method: "post",
        headers: { Authorization: "Bearer " + token, },
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
        data: this.myData,
      })
        .then((
        ) => {
          if (this.myTypeData.truckImg !== null) {
            this.setImg()
          }

        })
        .catch((
        ) => {
          alert("업데이트 실패")
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
          this.myData.category = res.data.category
          this.myData.description = res.data.description
          this.myData.name = res.data.name
          this.myData.phone = res.data.phone
          this.myTypeData.is_update = true

        })
        .catch(() => {
          alert("정보가져오기 실패");
        });
    },

    setImg() {
      var formData = new FormData();
      formData.append("file", this.myTypeData.truckImg);
      const token = localStorage.getItem("accessToken");
      axios({
        url: RF.foodtruck.setImg(),
        method: "post",
        headers: { Authorization: "Bearer " + token },
        data: formData,
      })
        .then(() => {
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
          if (res.data !== null) {
            this.drawTruckImg(res)
          }
        })
        .catch(() => {
          alert("이미지 가져오기 실패")
        });
    },
    // 아래 함수 임의 사용금지
    drawTruckImg(res) {

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
    },

  },
});
